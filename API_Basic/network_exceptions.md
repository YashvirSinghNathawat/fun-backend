# Network Exceptions — Step-by-Step Breakdown

## SocketTimeoutException: connect timed out

1. Client sends SYN
2. No SYN-ACK received
3. Client retries handshake
4. Timeout expires
5. Application gets `connect timed out`

> Connection was never established — the server is unreachable or too slow to respond.

---

## ConnectException: Connection refused

1. Client sends SYN
2. Server receives request
3. No process listening on port
4. Server sends RST immediately
5. Application gets `Connection refused`

> Server is reachable but nothing is listening on that port.

---

## UnknownHostException

1. Application performs DNS lookup
2. DNS resolution fails
3. Hostname cannot be resolved to IP
4. TCP connection never starts
5. Application gets `UnknownHostException`

> Fails before TCP even begins — the hostname is wrong or DNS is unavailable.

---

## NoRouteToHostException

1. Application tries to connect
2. OS checks routing table
3. No valid route to destination
4. Packet cannot be delivered
5. Application gets `No route to host`

> Network-level failure — the OS cannot find a path to the destination IP.

---

## SocketTimeoutException: Read timed out

1. TCP handshake succeeds
2. Connection established
3. Client sends request
4. Client waits for response
5. No data arrives within timeout
6. Application gets `Read timed out`

> Connection was established successfully, but the server took too long to respond.

---

## SocketException: Connection reset

1. TCP connection established
2. Data exchange is happening
3. Server forcefully closes connection (RST)
4. Client detects reset on next operation
5. Application gets `Connection reset`

> The server abruptly terminated the connection mid-communication.

---

## Broken pipe

1. TCP connection established
2. Server closes connection normally or forcefully
3. Client still tries to write data
4. OS detects closed socket state
5. OS says: "other side already closed"
6. Application gets `Broken pipe`

> Client tried to write to a connection the server had already closed.

---

## SSLHandshakeException

1. TCP handshake succeeds
2. SSL/TLS handshake begins
3. Certificate/protocol validation fails
4. Secure channel not established
5. Application gets `SSLHandshakeException`

> TCP layer is fine, but TLS negotiation failed (bad cert, mismatched protocol, untrusted CA).

---

## EOFException

1. TCP connection established
2. Client reads input stream
3. Server closes connection unexpectedly
4. Stream ends before expected data
5. Application gets `EOFException`

> Server closed the connection before sending all the expected data.
