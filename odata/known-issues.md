# OData Adapter — Known Issues

---

## OData V2

### Issue 1: GET_FEED Fails Mid-Pagination — Remote Host Terminates TLS Connection

**Problem:**

When using snapshot-based paging (`paging=snapshot`) with Process in Pages disabled, the adapter fetches all pages in a single execution loop. After a large number of successful pages, the remote server abruptly terminates the TLS connection during the handshake of a subsequent request — using a `$skiptoken` that the server itself issued in the previous response. No HTTP response is returned.

```
java.io.EOFException: SSL peer shut down incorrectly
Remote host terminated the handshake
```

The adapter retries the failed request but receives the same error on each attempt, ultimately throwing `OsciException` and failing the iFlow.

**Likely cause:** The `$skiptoken` for snapshot-based paging encodes the origin server node. If a subsequent request is routed to a different server node (e.g. due to load balancing), that node does not hold the snapshot and drops the connection.

**OData adapter verdict:** Working as expected. The adapter correctly constructs all requests, follows the server-issued skiptoken, and retries on failure. The fault is server-side.

**Recommendation:** Enable Process in Pages to make the flow resilient to mid-run connection drops, so each page is committed independently rather than the entire fetch failing as one unit.

---

## OData V4

### Issue 1: POST Fails — `sap-client` Not Appended to POST Request (Adapter ≤ 1.18)

**Problem:** `sap-client` configured under Custom Query Parameters in the Metadata section is only applied to the Metadata Call and Service Document Call — not to the actual POST request. The POST is sent without `sap-client`, causing the backend to process it against the wrong client/user.

**Resolution:** Upgrade the OData V4 Adapter to the latest version and configure `sap-client` under the query options that apply to POST requests.

---

### Issue 2: POST Fails on Subsequent Calls — Backend Does Not Return `Set-Cookie` on Service Document Call

**Background — Adapter Call Flow:**

The OData V4 adapter involves three possible calls:

- **Service Document Call** — fetches the CSRF token (and session cookie)
- **Metadata Call** — fetches EDMX metadata; also returns the CSRF token and session cookie
- **POST Call** — the actual data operation

To obtain the CSRF token and session cookie, the adapter first performs a Service Document Call. The backend is expected to return both the CSRF token and the `Set-Cookie` header. The adapter can also obtain these from the Metadata Call. If metadata is already cached, the Metadata Call is skipped and only the Service Document Call is executed.

**Problem:**

On the first request, the Metadata Call is made and the backend returns both the CSRF token and the session cookie — so the POST succeeds. On subsequent requests, metadata is cached so the adapter skips the Metadata Call and performs only the Service Document Call. The backend returns the CSRF token but not the `Set-Cookie` header. Without the session cookie, the POST request fails.

**Resolution:** The backend service must return the `Set-Cookie` header on the Service Document Call, not just on the Metadata Call. This is a backend-side fix.
