# SSL/TLS, TCP, HTTP & HTTPS — Revision Notes

---

## 1. Protocol Stack

```
Plain HTTP:          HTTPS:
  HTTP                HTTP
   │                   │
  TCP                 TLS 🔐
   │                   │
  IP                  TCP
                       │
                      IP
```

> **TCP connects → TLS secures → HTTP communicates**

- **HTTP** — application-layer protocol for exchanging messages (no encryption)
- **TLS** — security layer sitting between HTTP and TCP; provides encryption, integrity, auth
- **TCP** — transport layer; guarantees reliable, ordered delivery
- **IP** — network layer; handles routing between hosts

---

## 2. HTTP vs HTTPS

| | HTTP | HTTPS |
|---|---|---|
| Full form | Hypertext Transfer Protocol | HTTP Secure |
| Encryption | None | Via TLS |
| Port | 80 | 443 |
| Use | Plain web traffic | Sensitive / all modern traffic |

**HTTPS = HTTP + TLS.** TLS wraps the HTTP messages; HTTP itself is unchanged.

---

## 3. SSL vs TLS

| | SSL | TLS |
|---|---|---|
| Full form | Secure Sockets Layer | Transport Layer Security |
| Versions | 2.0, 3.0 | 1.0, 1.1 (deprecated), **1.2, 1.3** |
| Status | Obsolete — do not use | Modern standard |
| Successor | — | Yes, replaced SSL |

> People still say "SSL certificate" by habit. Technically it is a **TLS/digital certificate**.  
> SSL 3.0 was broken by the POODLE attack. TLS 1.0/1.1 also deprecated. Use **TLS 1.2 minimum**, prefer **TLS 1.3**.

---

## 4. Why TLS? — Three Security Goals

```
                 TLS
                  │
       ┌──────────┼──────────┐
       ▼          ▼          ▼
Confidentiality Integrity Authentication
       │          │          │
   Encryption  AEAD/MAC   Certificate
```

**Confidentiality** — Data cannot be read by a third party intercepting traffic.  
**Integrity** — Data cannot be silently modified in transit; any tampering is detected.  
**Authentication** — Client can verify it is talking to the real server (not an impersonator).

---

## 5. Encryption — Basics

```
Encryption:   Plaintext + Key  ──►  Ciphertext
Decryption:   Ciphertext + Key ──►  Plaintext
```

Without encryption, an attacker reading traffic sees `Username: yash, Password: abc123`.  
With TLS encryption, they see `8fA#2x...@91` — unreadable without the key.

---

## 6. Symmetric Encryption

**Same secret key** used for both encryption and decryption.

```
Key = K

Encrypt:   DEMO + K  →  GHPR
Decrypt:   GHPR + K  →  DEMO
```

| Pros | Cons |
|---|---|
| Very fast | Both parties must already share the key |
| Efficient for large data | Key distribution is the hard problem |
| Good for application data | If key is stolen, all data is exposed |

**Modern example:** AES (Advanced Encryption Standard)  
**Obsolete:** 3DES, RC4 — do not use.

---

## 7. Asymmetric Encryption

**Two mathematically related keys:** public key (shareable) + private key (secret).

```
Server generates:    🔓 Public Key  ─── shared with everyone
                     🔒 Private Key ─── kept secret, never shared

Client encrypts:     Message + 🔓 Public Key  →  Ciphertext
Server decrypts:     Ciphertext + 🔒 Private Key →  Message
```

What is encrypted with the public key **can only be decrypted with the matching private key**.

| Pros | Cons |
|---|---|
| No prior shared secret needed | Much slower than symmetric |
| Public key can be freely shared | Not suitable for bulk data |
| Used for identity and key establishment | |

**Examples:** RSA, ECC (Elliptic Curve Cryptography), ECDH/ECDHE

---

## 8. Symmetric vs Asymmetric — Summary

| | Symmetric | Asymmetric |
|---|---|---|
| Keys | One shared secret key | Public key + Private key |
| Speed | Very fast | Slow |
| Large data | Excellent | Impractical |
| Key distribution | Hard (must share secretly) | Easy (public key is public) |
| Main use in TLS | Encrypt application data | Authentication + key establishment |
| Examples | AES | RSA, ECDH/ECC |

---

## 9. Hybrid Encryption — What TLS Actually Does

TLS combines both to get the best of each:

```
Step 1: Asymmetric  →  authenticate server + establish/derive shared secret (key material)
Step 2: Symmetric   →  derive session keys from shared secret
Step 3: Symmetric   →  encrypt all actual HTTP data with session keys
```

**TLS uses both types of encryption for different purposes:**

| Phase | Encryption type | Purpose |
|---|---|---|
| Key exchange / handshake | Asymmetric (RSA / ECDHE) | Securely exchange the session key between client and server |
| Data transfer | Symmetric (AES / ChaCha20) | Encrypt all actual application data using the session key |

Asymmetric encryption is used **only** to establish the shared session key — once both sides have it, all subsequent data is encrypted symmetrically with that session key.

**Why not use asymmetric for everything?**  
Asymmetric is ~1000x slower. You can't use it for continuous bulk data.

**Why not use symmetric for key exchange?**  
You'd need a secure channel to share the key — which you don't have yet.

**Solution:** Use asymmetric just long enough to agree on a secret, then switch to symmetric.

---

## 10. Hashing

A hash function converts any input to a **fixed-size output** (the hash/digest).

```
Input     →  Hash Function  →  Fixed-size hash
"DEMO"    →  SHA-256         →  ABC123...
"DEMOX"   →  SHA-256         →  XYZ789...   (completely different — avalanche effect)
```

**Properties:**
- Same input always → same hash
- Different input → different hash (with high probability)
- **One-way:** you cannot reverse the hash to get the original input
- Small change in input → completely different hash

---

## 11. Hashing vs Encryption

| | Encryption | Hashing |
|---|---|---|
| Reversible? | Yes — with the key | No — one-way |
| Purpose | Confidentiality | Integrity verification, fingerprinting |
| Key needed? | Yes | No |
| Output size | Variable (same as input) | Fixed |
| Example use | Encrypting messages | Password storage, integrity checks |

> **Do not confuse these.** Hashing passwords ≠ encrypting passwords. Hashed passwords cannot be "decrypted."

---

## 12. Hashing for Integrity

```
Sender:   Message = "Transfer ₹100"
          hash("Transfer ₹100")  =  ABC123
          ──► sends: (message + hash)

Receiver: receives "Transfer ₹100" + ABC123
          hash("Transfer ₹100")  =  ABC123  ✅  match → not modified

Attacker changes to "Transfer ₹900":
          hash("Transfer ₹900")  =  XYZ789  ❌  mismatch → tampered
```

The receiver detects tampering without needing to decrypt anything.

---

## 13. MAC — Message Authentication Code

A MAC improves on a plain hash by including a **secret key**:

```
Plain hash:   hash(Message)             →  anyone can compute
MAC:          MAC(Message + Secret Key) →  only parties with the key can compute/verify
```

MAC provides:
- **Integrity** — message was not changed
- **Authentication** — message came from someone who holds the secret key

In TLS, modern cipher modes (AEAD) combine encryption + MAC in one operation.

---

## 14. Common Hash Algorithms

| Algorithm | Output size | Status |
|---|---|---|
| MD5 | 128 bits | Broken — collision attacks known, don't use for security |
| SHA-1 | 160 bits | Deprecated — collision attacks demonstrated |
| SHA-256 | 256 bits | Modern, widely used |
| SHA-384 | 384 bits | Modern |
| SHA-512 | 512 bits | Modern |

> Use **SHA-2 family (SHA-256 minimum)** or SHA-3 for modern security.

---

## 15. Digital Certificates

A TLS certificate is an **X.509 document** that binds a public key to an identity.

A certificate answers: **"Does this public key really belong to example.com?"**

Without a certificate, anyone could send you their public key and claim to be example.com (man-in-the-middle attack). The certificate, signed by a trusted CA, proves the binding between the domain and the public key.

```
Certificate contains:
 ├── Subject / Common Name (CN)   (identity the cert is issued to, e.g. www.example.com)
 ├── Subject Alternative Names (SAN)  (additional hostnames the cert covers)
 ├── Public Key 🔓               (RSA or EC key — the server's public key)
 ├── Issuer                      (CA that signed this cert)
 ├── Validity period             (Not Before / Not After)
 ├── Serial Number
 ├── Signature Algorithm
 └── CA's Digital Signature      (proves the CA vouches for this cert)
```

> **CN vs SAN:** CN is the primary identity (legacy). SANs are the modern way to list all hostnames a cert covers. Browsers now require SANs; CN alone is ignored.

---

## 16. Certificate Authority (CA)

A **CA** is a trusted third party that issues and digitally signs certificates.

```
CA signs certificate  →  "I vouch that example.com owns this public key"

Client OS/browser ships with a list of trusted root CAs (trust store).
If the cert chain leads back to a trusted root CA → ✅ trusted
If not → ❌ "Certificate not trusted" warning
```

**Chain of trust:**
```
Root CA           (self-signed, pre-installed in OS/browser trust store)
   │
   └── Intermediate CA   (signed by Root CA)
            │
            └── End-Entity cert  (signed by Intermediate CA — the server's cert)
```

Browser validates the chain up to a trusted root CA.

---

## 17. Certificate Verification (Client-side)

When client receives server certificate during TLS handshake:

```
① Is the CA trusted?          — is the issuer in my trust store?
② Is the CA signature valid?  — does the signature verify with CA's public key?
③ Is it not expired?          — current date within Valid From/To?
④ Hostname match?             — cert issued for example.com, am I at example.com?
⑤ Not revoked?                — CRL / OCSP check (optional/configurable)

All pass → ✅ Certificate trusted
Any fail → ❌ TLS error / browser warning
```

---

## 18. Certificate Formats

| Format | Encoding | Extension | Use |
|---|---|---|---|
| PEM | Base64 text | `.pem`, `.crt`, `.cer` | Most common; servers, certs, keys |
| DER | Binary | `.der`, `.cer` | Binary form of PEM |
| PKCS#7 | Base64 / Binary | `.p7b`, `.p7c` | Certificate chains (no private key) |
| PKCS#12 | Binary | `.p12`, `.pfx` | Bundle: cert + private key + chain |

**PEM example header:**
```
-----BEGIN CERTIFICATE-----
(base64 data)
-----END CERTIFICATE-----
```

**PKCS#12 is important:** `.p12`/`.pfx` bundles the private key with the cert — used when you need to import both together (e.g., into a Java keystore, IIS, or browser).

---

## 19. Inspecting a Certificate with OpenSSL

```bash
openssl x509 -in certificate.crt -text
```

- `-in`   — input file (PEM or DER cert)
- `-text` — print all fields in human-readable form (subject, issuer, SAN, validity, public key, signature, etc.)

**Useful variants:**
```bash
openssl x509 -in certificate.crt -text -noout        # suppress the raw base64 at the end
openssl x509 -in certificate.crt -noout -subject     # print only the Subject / CN
openssl x509 -in certificate.crt -noout -issuer      # print only the Issuer
openssl x509 -in certificate.crt -noout -dates       # print only Not Before / Not After
openssl x509 -in certificate.crt -noout -fingerprint # print SHA-1 fingerprint
```

---

## 20. TCP — Three-Way Handshake

TCP runs **before** TLS. You need a TCP connection before TLS can start.

TCP is about **reliable transport** — not security.

```
Client ──── SYN ─────────────────► Server    "I want to connect. My seq = X"
Client ◄─── SYN + ACK ────────────  Server    "OK. My seq = Y, ack = X+1"
Client ──── ACK ─────────────────► Server    "Confirmed. ack = Y+1"
            ── TCP CONNECTION ESTABLISHED ──
```

**SYN** = synchronize sequence numbers  
**ACK** = acknowledge receipt

After this, both sides have an established, reliable connection. Now TLS can start.

---

## 21. TCP vs TLS — They Are Different

| | TCP | TLS |
|---|---|---|
| Purpose | Reliable transport | Security |
| Provides | Connection, ordering, retransmission | Encryption, integrity, authentication |
| Handshake | SYN / SYN-ACK / ACK | ClientHello / ServerHello / Certificate / Keys |
| Layer | Transport | Between Application and Transport |
| Knows about security? | No | Yes |

> **TCP makes sure packets arrive. TLS makes sure no one can read or tamper with them.**

---

## 22. TLS 1.2 Handshake — Step by Step (2 RTT)

Goal: authenticate the server and establish session keys.

```
CLIENT                                          SERVER
  │                                                │
  │── ① ClientHello ──────────────────────────►  │  TLS versions, cipher suites, client random (nonce)
  │                                                │
  │◄─ ② ServerHello + Certificate ───────────────  │  chosen cipher suite, server random, cert (public key)
  │                                                │
  │   🔍 Verify Certificate                       │
  │                                                │
  │── ③ ClientKeyExchange ─────────────────────►  │  Pre-Master Secret encrypted with server's 🔓 public key
  │── ③ ChangeCipherSpec ──────────────────────►  │  "switching to encrypted mode now"
  │── ③ Finished ──────────────────────────────►  │  MAC of entire handshake
  │                                                │
  │◄─ ④ ChangeCipherSpec ─────────────────────────  │  server confirms switch
  │◄─ ④ Finished ──────────────────────────────────  │  server confirms handshake integrity
  │                                                │
  │════════════ TLS Channel 🔒 ════════════════════│
  │── 🔒 Application Data ─────────────────────►  │
```

**Key derivation in TLS 1.2:**
```
Pre-Master Secret (PMS)  +  Client Random  +  Server Random
                              │
                         PRF (pseudo-random function)
                              │
                    Master Secret  →  Session Keys
```

**Problem:** RSA key exchange has **no forward secrecy**.  
If the server's private key is compromised later, an attacker who recorded the session can decrypt the PMS and derive session keys → all past traffic exposed.

---

## 23. TLS 1.3 Handshake — Step by Step (1 RTT)

TLS 1.3 merges steps and sends the key share upfront in ClientHello — saving one full round trip.

```
CLIENT                                          SERVER
  │                                                │
  │── ① ClientHello + Key Share ───────────────►  │  TLS version, cipher suites, ECDHE public key share
  │                                                │
  │◄─ ② ServerHello + Key Share ──────────────────  │  chosen params, server ECDHE public key share
  │◄─ ② Certificate + Finished ───────────────────  │  cert + handshake MAC (already encrypted!)
  │                                                │
  │   🔍 Verify Certificate                       │
  │                                                │
  │── ③ Finished ──────────────────────────────►  │  client confirms handshake integrity
  │                                                │
  │════════════ TLS Channel 🔒 ════════════════════│
  │── 🔒 Application Data (1 RTT) ──────────────►  │
```

**Key establishment via ECDHE:**
```
Client: generates ephemeral key pair  →  sends public part in ClientHello
Server: generates ephemeral key pair  →  sends public part in ServerHello
Both:   compute shared secret via ECDHE math (neither side transmitted the secret itself)
        Ephemeral keys discarded after session
```

**Forward secrecy:** Even if the server's long-term private key is stolen later, past sessions cannot be decrypted — the ephemeral ECDHE keys are already gone.

**0-RTT resumption** (returning clients only):
```
Client sends application data immediately with ClientHello using a pre-shared key from prior session.
Caveat: replay attacks possible — use only for idempotent requests (e.g. GET, not POST payments).
```

**What TLS 1.3 removed:**
```
No RSA key exchange        (no forward secrecy)
No RC4, DES, 3DES          (broken ciphers)
No MD5, SHA-1              (weak hashes)
No renegotiation           (attack surface)
No compression             (CRIME attack vector)
No custom DHE groups       (weak parameter risk)
```

---

## 24. Session Keys

After key establishment, both sides run a **Key Derivation Function (KDF)** on the shared secret:

```
Shared Secret + Client Random + Server Random
                     │
                 KDF (e.g. HKDF)
                     │
          ┌──────────┼──────────┐
          ▼          ▼          ▼
   Client write   Server write  MAC keys /
      key           key         IV material
```

These symmetric session keys encrypt all subsequent HTTP data.

---

## 24a. ChangeCipherSpec

A 1-byte TLS signal meaning **"switch to encrypted mode now."** Sent by both client and server before their `Finished` message. Everything after it is encrypted with the negotiated session keys.

Removed in TLS 1.3 (may appear as a dummy for middlebox compatibility, but carries no meaning).

---

## 25. AEAD — Authenticated Encryption with Associated Data

Modern TLS (1.3) uses AEAD cipher modes (e.g. AES-GCM, ChaCha20-Poly1305).

AEAD combines encryption + integrity in a **single operation**:

```
AEAD Encrypt:   Plaintext + Key + Nonce  →  Ciphertext + Auth Tag
AEAD Decrypt:   Ciphertext + Auth Tag + Key + Nonce  →  Plaintext (or ❌ if tampered)
```

- **Auth Tag** acts as the MAC — detects any modification
- One call gives you both **confidentiality** and **integrity**
- No separate MAC step needed

---

## 26. Cipher Suites

A cipher suite defines the cryptographic algorithms used for a TLS session.

**TLS 1.3 cipher suites** (only the AEAD + hash — key exchange is separate):
```
TLS_AES_128_GCM_SHA256          ← AES-128 in GCM mode, SHA-256 for HKDF
TLS_AES_256_GCM_SHA384          ← AES-256 in GCM mode, SHA-384 for HKDF
TLS_CHACHA20_POLY1305_SHA256    ← ChaCha20 stream cipher, Poly1305 MAC
```

**TLS 1.2 cipher suite format** (older, more explicit):
```
TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256
  │      │           │           │
  │      │           │           └── MAC hash
  │      │           └── Symmetric cipher
  │      └── Authentication (server cert type)
  └── Key exchange algorithm
```

---

## 27. TLS 1.2 vs TLS 1.3 — Comparison

| | TLS 1.2 | TLS 1.3 |
|---|---|---|
| RFC | RFC 5246 (2008) | RFC 8446 (2018) |
| Handshake | 2 RTT | 1 RTT (0-RTT resumption available) |
| Key exchange | RSA or ECDHE | ECDHE **only** |
| Forward secrecy | Optional | Mandatory |
| Cipher suites | Many, including weak | Only AEAD (AES-GCM, ChaCha20-Poly1305) |
| Removed algorithms | Allows RC4, 3DES, MD5, SHA-1 | All removed |
| Certificate encryption | Certificate sent in plaintext | Certificate encrypted |
| Renegotiation | Supported (attack surface) | Removed |
| Compression | Supported (CRIME attack) | Removed |

```
TLS 1.2 handshake flow:   ClientHello → ServerHello+Cert → ClientKeyExchange+CCS+Finished → CCS+Finished  (2 RTT)
TLS 1.3 handshake flow:   ClientHello+KeyShare → ServerHello+KeyShare+Cert+Finished → Finished            (1 RTT)
```

> Prefer TLS 1.3. TLS 1.2 still acceptable where 1.3 is not supported.

---

## 28. What Can an Attacker See in HTTPS?

TLS protects **application data** (HTTP request/response content).

An attacker observing the network **can** still see:
```
Source IP address
Destination IP address
Port number (443)
Packet sizes and timing
SNI (Server Name Indication) — the hostname in Client Hello (unencrypted in TLS 1.2/1.3, though TLS 1.3 has ECH to encrypt it)
```

An attacker **cannot** see:
```
HTTP method, URL path, query params     (GET /bank/account?id=123)
HTTP headers                            (Authorization, Cookie)
HTTP body                               (username, password, response data)
```

---

## 29. Complete HTTPS Connection — Full Sequence

```
CLIENT                                                    SERVER
  │                                                          │
  │  ─────────────── ① TCP HANDSHAKE ─────────────────     │
  │                                                          │
  │────────── SYN ──────────────────────────────────────►  │
  │◄───────── SYN + ACK ───────────────────────────────────  │
  │────────── ACK ──────────────────────────────────────►  │
  │                                                          │
  │  ─────────────── ② TLS HANDSHAKE ─────────────────     │
  │                                                          │
  │────────── Client Hello ─────────────────────────────►  │
  │◄───────── Server Hello ──────────────────────────────    │
  │◄───────── Certificate ───────────────────────────────    │
  │                                                          │
  │  🔍 Verify Certificate (trusted CA, valid, hostname)    │
  │                                                          │
  │────────── Key Establishment (ECDHE) ────────────────►  │
  │                                                          │
  │  🔑 Session Keys derived (both sides independently)     │
  │                                                          │
  │═══════════════ TLS Channel 🔒 ══════════════════════════│
  │                                                          │
  │  ─────────────── ③ HTTP COMMUNICATION ──────────────   │
  │                                                          │
  │────────── 🔒 GET /index.html ───────────────────────►  │
  │◄───────── 🔒 200 OK + body ──────────────────────────    │
```

---

## 30. Where HTTP Sits

HTTP creates messages. TLS protects them. TCP delivers them.

```
Application:   GET /users HTTP/1.1
                    │
               HTTP message
                    │
               TLS encrypts
                    │
               TCP segments
                    │
               IP packets
                    │
               Network
```

HTTP does not know or care about encryption. It just makes requests and responses. TLS is transparent to HTTP.

---

## 31. CertificateRequest and Mutual TLS (mTLS)

Yes, but **only** when the server requires the client to authenticate using a client certificate — this is called **Mutual TLS (mTLS)**. In normal HTTPS, the server does not send a CertificateRequest.

**Normal TLS** — server authenticates to client only:
```
Client verifies Server ✅
Server trusts Client  ✗  (no client certificate)
```

**mTLS** — both sides authenticate each other:
```
Client verifies Server ✅
Server verifies Client ✅
```

**mTLS handshake flow:**

```
CLIENT                                SERVER
  │                                     │
  │── ClientHello ─────────────────────►│
  │                                     │
  │◄─ ServerHello + Certificate ────────│
  │◄─ CertificateRequest ───────────────│  ← server requests client cert
  │◄─ ServerHelloDone ──────────────────│
  │                                     │
  │── Client Certificate ──────────────►│  ← client sends its cert
  │── ClientKeyExchange ───────────────►│
  │── CertificateVerify ───────────────►│  ← proves client holds the private key
  │── ChangeCipherSpec + Finished ──────►│
  │                                     │
  │◄─ ChangeCipherSpec + Finished ───────│
  │                                     │
  │══════════ Encrypted TLS Channel ════│
  │                                     │
  │── 🔒 HTTP Request ─────────────────►│
```

**CertificateVerify** — the client signs a hash of the entire handshake transcript with its private key. The server verifies it using the public key from the client's certificate. This proves the client actually holds the private key matching the certificate it sent.

> `CertificateRequest` is a **TLS handshake message** — not an HTTP request. It happens at the transport security layer before any HTTP communication.

**Where mTLS is used:**

| Use case | Why |
|---|---|
| Service-to-service (microservices) | Each service proves its identity, not just the server |
| API gateways / zero-trust networks | Client must present a valid cert to access the API |
| Corporate internal systems | Only devices with a corp-issued cert can connect |
| Kubernetes / Istio service mesh | Automatic mTLS between pods |

---

## 32. Incomplete TLS Handshake — Client Goes Silent

If the client sends `ClientHello`, the server responds with `ServerHello + Certificate`, but the client sends nothing further, the TLS handshake remains incomplete.

```
CLIENT                         SERVER
  │── ClientHello ────────────►│
  │◄─ ServerHello ─────────────│
  │◄─ Certificate ─────────────│
  │                             │
  │   (no response)             │  ← server waits for ClientKeyExchange
  │                             │
  │                  timeout    │
  │                             │
  │◄─ TLS Alert (optional) ─────│  close_notify or handshake_failure
  │◄─ TCP FIN / RST ────────────│  connection closed
```

- No HTTP request is sent — HTTP only runs after TLS completes
- The server eventually hits a **handshake timeout** and closes the connection
- Closure may be:
  - **TCP FIN** — graceful close
  - **TCP RST** — abrupt close
  - **TLS Alert** before closing (`close_notify` or `handshake_failure`)

