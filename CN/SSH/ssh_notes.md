# SSH — Secure Shell

---

## 1. What is SSH?

SSH (Secure Shell) is a network protocol used to securely connect to and communicate with a remote machine over an untrusted network.

It provides:

- **Confidentiality** → data is encrypted
- **Integrity** → data cannot be silently modified
- **Authentication** → verifies the server and authenticates the user
- **Secure remote access** → execute commands on another machine
- **Secure file transfer** → SCP / SFTP
- **Port forwarding / tunneling** → securely transport other network connections

The standard SSH server listens on **TCP port 22**.

```
Your Computer (SSH Client)                     Remote Server (SSH Server)
        │                                               │
        │─────────────── Secure SSH Connection ────────►│
        │                                               │
        │◄══════════ Commands / Files / Tunnels ═══════►│
```

---

## 2. What was Telnet?

Telnet is an older protocol for remote terminal access. Default port: **TCP 23**.

The major problem: **Telnet does not encrypt communication.**

```
CLIENT                              SERVER
  │──── username ─────────────────►│
  │──── password ──────────────────►│
  │──── command ───────────────────►│
  │◄─── response ───────────────────│
  │                                 │
  └──── Everything in plaintext ────┘
```

Someone observing the network traffic could see:
```
username: yash
password: MyPassword123
command: ls -la
```

SSH was designed to replace Telnet with secure remote access.

### Telnet vs SSH

| | Telnet | SSH |
|---|---|---|
| Default port | 23 | 22 |
| Encryption | ❌ No | ✅ Yes |
| Authentication | Weak / insecure | Password, keys, etc. |
| Integrity protection | ❌ | ✅ |
| Secure remote shell | ❌ | ✅ |
| File transfer | ❌ | SCP / SFTP |
| Port forwarding | ❌ | ✅ |
| Modern usage | Mostly legacy / troubleshooting | Widely used |

> Telnet itself is not inherently "bad" — it is simply unsuitable for transmitting sensitive information over an untrusted network.

---

## 3. What Can We Do With SSH?

### 1. Remote shell
```bash
ssh user@server
```
```
$ ssh yash@server.example.com
server$ ls
server$ cd /var/log
server$ cat application.log
```

### 2. Execute a single remote command
```bash
ssh user@server "ls -la"
```
The command executes remotely and output comes back to you.

### 3. Secure file transfer
```bash
scp file.txt user@server:/tmp/    # SCP
sftp user@server                  # SFTP
```

### 4. Port forwarding / tunneling

SSH can create an encrypted tunnel between machines for accessing services not directly exposed to the internet.

### 5. Automation

SSH is heavily used in DevOps, CI/CD pipelines, deployment scripts, and cloud infrastructure.

---

## 4. SSH Client vs SSH Server

**SSH Client** — initiates the connection. Examples: `ssh`, `scp`, `sftp`

**SSH Server** — accepts SSH connections. On Linux, commonly runs `sshd` (d = daemon).

```
Laptop (SSH Client: ssh)                Linux Server (SSH Server: sshd)
        │                                          │
        │─────────────── TCP 22 ──────────────────►│
```

A machine can be both a client and a server.

---

## 5. Why Port 22?

SSH conventionally uses TCP 22.

```bash
ssh user@192.168.1.10        # connects to 192.168.1.10:22
ssh -p 2222 user@server      # use a different port
```

> You may see advice to change 22 → 2222 to reduce automated bot/scanner noise. This is not a real security mechanism — a scanner can scan any port. More important: use SSH keys, disable password auth, disable root login, use a firewall, and keep SSH updated.

---

## 6. SSH Connection — Big Picture

```
CLIENT                                                    SERVER
  │                                                          │
  │── 1. TCP Handshake ─────────────────────────────────────│
  │── 2. SSH Version Exchange ──────────────────────────────│
  │── 3. Algorithm Negotiation ─────────────────────────────│
  │── 4. Key Exchange (e.g. Diffie-Hellman) ────────────────│
  │── 5. Host Verification ─────────────────────────────────│
  │                                                          │
  │═══════════════ Encrypted SSH Connection ════════════════│
  │                                                          │
  │── 6. User Authentication ───────────────────────────────│
  │                                                          │
  │═══════════════ Secure Session ══════════════════════════│
```

---

## 7. SSH Protocol Layers

SSH is structured as three layers, all running over TCP port 22:

```
┌─────────────────────────────────────────────────────┐
│  Connection Layer                                   │
│  Multiplexes logical channels: shell, SCP, tunnels  │
├─────────────────────────────────────────────────────┤
│  User Authentication Layer                          │
│  Proves client identity: password, key, certificate │
├─────────────────────────────────────────────────────┤
│  Transport Layer                                    │
│  Establishes encrypted channel, verifies host key   │
├─────────────────────────────────────────────────────┤
│  TCP (port 22)                                      │
└─────────────────────────────────────────────────────┘
```

| Layer | Responsibility |
|---|---|
| Transport | Encryption, integrity, server host key verification |
| User Authentication | Authenticates the client (password, public key, certificate) |
| Connection | Multiplexes multiple logical channels over one SSH connection |

The layers run in order — Transport first, then Authentication, then Connection. You cannot authenticate before the encrypted channel exists, and you cannot open channels before authenticating.

---

## 8. Step 1 — TCP Handshake + SSH Version Exchange

SSH runs on top of TCP. TCP connection is established first.

```
CLIENT                              SERVER
  │──── SYN ──────────────────────►│
  │◄─── SYN + ACK ─────────────────│
  │──── ACK ──────────────────────►│
  │        TCP connection established
```

Then both sides exchange SSH identification strings:
```
Client → SSH-2.0-OpenSSH_10.x
Server → SSH-2.0-OpenSSH_9.x
```

> TCP establishes the connection. SSH version exchange establishes protocol compatibility. TCP itself does not encrypt anything.

---

## 9. Step 2 — Algorithm Negotiation

Client and server advertise supported algorithms and agree on what to use.

```
CLIENT                              SERVER
  │──── Supported algorithms ─────►│
  │◄─── Supported algorithms ───────│
  │        common set selected
```

Categories negotiated:
- **Encryption:** AES-GCM, ChaCha20-Poly1305
- **Key exchange:** Curve25519, Diffie-Hellman
- **Host key type:** Ed25519, RSA

---

## 9. Step 3 — Key Exchange

Client and server establish a shared secret without sending it across the network (e.g. Diffie-Hellman / ECDH).

```
CLIENT                                                    SERVER
  │──── public parameters ─────────────────────────────►│
  │◄─── public parameters ──────────────────────────────│
  │                                                       │
  │  (client derives shared secret)   (server derives shared secret)
  │                   └─────── same value ───────────────┘
```

An attacker observing the exchange cannot derive the shared secret. This secret is then used to derive symmetric session keys.

---

## 10. Symmetric Session Keys

After key exchange, SSH uses symmetric encryption for all session data (much faster than asymmetric).

```
CLIENT                                                    SERVER
  │                                                          │
  │  Key Exchange → Shared Secret → Session Key(s)          │
  │                                                          │
  │──── Encrypted data ─────────────────────────────────►  │
  │◄─── Encrypted data ──────────────────────────────────   │
```

---

## 11. Host Verification — Preventing MITM

When connecting to `server.example.com`, how do you know you're talking to the real server?

```
CLIENT              ATTACKER              REAL SERVER
  │──── SSH ───────►│──── SSH ──────────►│
  │◄─── Fake ───────│                    │
```

SSH uses the server's **host key** to detect this.

---

## 12. known_hosts and TOFU

**SSH has no built-in PKI.** Unlike TLS, there is no Certificate Authority that vouches for the server's identity. Instead, SSH uses **Trust On First Use (TOFU)**.

**First connection:**
```
The authenticity of host 'server.example.com' can't be established.
ED25519 key fingerprint is SHA256:abc123...
Are you sure you want to continue connecting (yes/no)?
```
- Server presents its host public key
- Client has no prior record — cannot verify it automatically
- If accepted, key is stored in `~/.ssh/known_hosts`

**Subsequent connections — how the server proves its identity:**

The server doesn't just send the public key again (anyone could do that). It proves possession of the matching private key by **signing the exchange hash H** (a hash of handshake data including both sides' randoms and key material):

```
CLIENT                                          SERVER
  │◄─── host public key ───────────────────────│
  │◄─── signature over exchange hash H ────────│  signed with host private key 🔐
  │   verify signature using saved public key
  │   match → server is genuine ✅
  │   mismatch → WARNING ❌
```

**Key mismatch warning:**
```
WARNING: REMOTE HOST IDENTIFICATION HAS CHANGED!
```

Could indicate a MITM attack — but also has legitimate causes (server rebuilt, keys regenerated, DNS now points elsewhere). Investigate before blindly removing the entry.

> **TOFU vs PKI:** TLS relies on CAs to pre-validate server identity. SSH skips that — you trust the key the first time you see it, then verify it on every subsequent connection. Security depends on that first connection being clean.

---

## 13. SSH Authentication

After the secure channel is established, the server needs to know: **who are you?**

Two common methods:
1. Password authentication
2. Public-key authentication

---

## 14. Authentication Method 1 — Password

```bash
ssh yash@server
# yash@server's password: _
```

The password travels inside the encrypted SSH channel — not as plaintext over the network.

```
CLIENT                              SERVER
  │════ Encrypted SSH Channel ════►│
  │──── password (encrypted) ─────►│
  │◄─── Authentication OK ──────────│
```

**Problem:** passwords are vulnerable to brute-force, guessing, credential reuse, phishing. SSH key auth is preferred for administrative access.

---

## 15. Authentication Method 2 — SSH Key Pair

Uses asymmetric cryptography. You generate two keys:

```
~/.ssh/
  ├── id_ed25519        ← Private key 🔐  (never share)
  └── id_ed25519.pub    ← Public key  🔓  (goes to server)
```

**Rule:** Private key stays with you. Public key goes to the server.

---

## 16. How SSH Key Authentication Works

```
CLIENT                                          SERVER
  │                         authorized_keys has your public key 🔓
  │──── "I want to authenticate as yash" ─────►│
  │◄─── Challenge ──────────────────────────────│
  │   sign challenge with private key 🔐
  │──── Signature ─────────────────────────────►│
  │                   verify signature with 🔓
  │◄─── Authentication successful ──────────────│
```

The private key is **never sent** to the server.

---

## 17. Generate an SSH Key Pair

```bash
ssh-keygen -t ed25519
# saves to: ~/.ssh/id_ed25519  and  ~/.ssh/id_ed25519.pub
```

---

## 18. Copy Public Key to Server

```bash
ssh-copy-id user@server
```

Or manually append `~/.ssh/id_ed25519.pub` to `~/.ssh/authorized_keys` on the server.

```
CLIENT                              SERVER
id_ed25519     🔐 (stays here)      authorized_keys ← id_ed25519.pub copied here
id_ed25519.pub 🔓 ─── copy ────────►
```

---

## 19. Why Use a Passphrase on the Private Key?

If someone steals your private key file, a passphrase stops them from using it:

```
Private Key + Passphrase → can authenticate
Private Key alone (no passphrase) → can authenticate immediately ← risky
```

The passphrase protects the private key **at rest**.

---

## 20. SSH Config File

Instead of typing:
```bash
ssh -p 2222 -i ~/.ssh/company_key yash@very-long-server-name.example.com
```

Configure `~/.ssh/config`:
```
Host myserver
    HostName server.example.com
    User yash
    Port 2222
    IdentityFile ~/.ssh/company_key
```

Then just:
```bash
ssh myserver
```

---

## 21. SSH Port Forwarding

SSH can create an encrypted tunnel. Three types:

### Local Port Forwarding
```bash
ssh -L local_port:destination:dest_port user@ssh_server
```

```
Your Laptop        SSH Server        Internal Service
localhost:8080 ════SSH tunnel═════► server ──────────► db.internal:5432
     │
     └── your app connects here, traffic forwarded to db
```

### Remote Port Forwarding
```bash
ssh -R remote_port:destination:dest_port user@server
```

Exposes a local service through a port on the remote server.

### Dynamic Port Forwarding — SOCKS Proxy
```bash
ssh -D 1080 user@server
```

```
Application → localhost:1080 (SOCKS) ════SSH tunnel════► SSH Server → Destination
```

---

## 22. SCP — Secure Copy

```bash
scp file.txt user@server:/tmp/      # upload
scp user@server:/tmp/file.txt .     # download
```

```
Upload:   Laptop: file.txt ════SSH════► Server: /tmp/file.txt
Download: Laptop: file.txt ◄═══SSH═════Server: /tmp/file.txt
```

---

## 23. SFTP — SSH File Transfer Protocol

Interactive file management over SSH.

```bash
sftp user@server
sftp> ls / cd / get file.txt / put local.txt / mkdir test
```

### SCP vs SFTP

| | SCP | SFTP |
|---|---|---|
| Purpose | Copy files | File transfer + management |
| Interactive | Limited | Yes |
| Runs over SSH | Yes | Yes |
| Directory operations | Limited | Rich |

---

## 24. SSH Agent

Holds unlocked private keys in memory so you don't re-enter the passphrase repeatedly.

```bash
eval "$(ssh-agent -s)"
ssh-add ~/.ssh/id_ed25519
```

```
SSH Agent (holds 🔐 in memory) ──► Server A
                               └──► Server B
```

---

## 25. SSH Agent Forwarding

Allows SSH-from-a-jump-server without placing your private key on the jump server.

```
Laptop (SSH Agent holds 🔐) ════SSH════► Jump Server ════SSH════► Internal Server
         ↑                                     │
         └─────── auth request forwarded ───────┘
```

```bash
ssh -A user@jump-server          # -A enables agent forwarding
# then from jump server:
ssh user@internal-server
```

> **Security warning:** A compromised jump server can use your forwarded agent to authenticate as you while the session is active. Use agent forwarding only on trusted hosts.

---

## 26. Complete SSH Flow

```
CLIENT                                                    SERVER
  │                                                          │
  │── ① SYN ──────────────────────────────────────────────►│
  │◄─ ② SYN + ACK ─────────────────────────────────────────│
  │── ③ ACK ──────────────────────────────────────────────►│
  │                    TCP established                       │
  │                                                          │
  │── SSH-2.0-OpenSSH_10.x ───────────────────────────────►│
  │◄─ SSH-2.0-OpenSSH_9.x ─────────────────────────────────│
  │                    version exchange done                 │
  │                                                          │
  │◄══ algorithm negotiation ════════════════════════════►  │
  │◄══ key exchange (ECDH / DH) ═════════════════════════►  │
  │                    session keys derived                  │
  │                                                          │
  │   verify server host key against known_hosts             │
  │                                                          │
  │════════════════ Encrypted SSH Channel ══════════════════│
  │                                                          │
  │── user authentication (password / key) ───────────────►│
  │◄─ Authentication OK ────────────────────────────────────│
  │                                                          │
  │════════════════ Secure Session ═════════════════════════│
  │◄══ commands / files / port forwarding ══════════════════│
```
