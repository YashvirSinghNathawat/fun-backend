# OData Concepts

## ETags and If-Match in OData

### What is If-Match?

In SAP OData, the `If-Match` HTTP header is mainly used for **optimistic concurrency control**. It tells the server:

> "Perform this update/delete only if the resource has not changed since the version I previously retrieved."

---

### Basic Flow

**Step 1 — Retrieve the resource:**

```http
GET /Products('100')
```

Server responds with an ETag:

```http
ETag: W/"'20260821'"
```

**Step 2 — Update with If-Match:**

```http
PUT /Products('100')
If-Match: W/"'20260821'"
Content-Type: application/json

{
  "Name": "New Product Name"
}
```

The server compares the `If-Match` value with the current ETag:

- ETag matches → update is allowed
- ETag doesn't match → server returns `HTTP 412 Precondition Failed`

---

### Why Is This Needed?

Imagine two users retrieve the same product:

```
User A → GET → ETag = 123
User B → GET → ETag = 123
```

User A updates it:

```
PUT + If-Match: 123
→ Success
→ ETag becomes 124
```

Now User B tries:

```
PUT + If-Match: 123
→ Server sees current ETag is 124, not 123
→ Rejected (412)
```

This prevents User B from unknowingly overwriting User A's changes.

---

### Header Roles: ETag vs If-Match

| Header | Direction | Purpose |
|---|---|---|
| `ETag` | Response header | Server sends the current resource version |
| `If-Match` | Request header | Client sends the version it expects to be current |

```
GET
 ↓
Server
 ↓
ETag: "123"         ← response header
 ↓
Client stores ETag
 ↓
PUT/PATCH/DELETE
If-Match: "123"     ← request header
 ↓
Server validates ETag
```

---

### If-Match: *

You may also encounter:

```http
If-Match: *
```

This generally means: "Proceed as long as the resource exists — don't require a specific ETag."

The exact behavior depends on the OData implementation and operation.

---

### SAP CPI OData Adapter

For the SAP CPI OData adapter, `If-Match` is particularly relevant when doing `PUT`, `PATCH`, or `DELETE`. The adapter may need to:

1. Obtain an ETag from a previous `GET` request
2. Send it as `If-Match` on the subsequent mutating request when the backend requires concurrency checking
