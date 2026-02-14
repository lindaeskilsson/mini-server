# Mini HTTP Server (Java)

A minimal web server built **from scratch using pure Java sockets**.  
No frameworks. No Spring. Just HTTP.

The purpose of this project is to understand what actually happens behind modern backend frameworks by implementing the core mechanics manually.

---

## ✨ Features

- Accepts real browser connections
- Parses HTTP requests (method, path, headers)
- Routes requests based on URL
- Generates valid HTTP responses
- Automatic `404 Not Found`
- Clear separation between networking and application logic

You can open it in Chrome and it behaves like a real backend server.

---

## 🏗 Architecture

Browser
↓
TCP Connection
↓
SocketServer
↓
HttpParser
↓
Router
↓
HttpResponse
↓
Browser


### Responsibility per component

| Component | Responsibility |
|--------|------|
**SocketServer** | Handles TCP connections |
**HttpParser** | Converts raw request text → `HttpRequest` object |
**Router** | Chooses which handler should run |
**HttpResponse** | Builds valid HTTP response text |

---

## 🌐 Example Routes

| Route | Result |
|------|------|
`/` | Welcome message |
`/health` | OK |
unknown path | 404 Not Found |

---

## 🚀 Run the server

### 1. Clone
```bash
git clone https://github.com/lindaeskilsson/mini-server.git
cd mini-server
2. Start
Run via IDE or terminal:

mvn compile
mvn exec:java -Dexec.mainClass="org.example.index.http.MyServer"
Server starts on:

http://localhost:4000
🧪 Test
Browser:

http://localhost:4000/
http://localhost:4000/health
http://localhost:4000/anything
Terminal:

curl -v http://localhost:4000/health
📚 Implemented HTTP Concepts
Request line parsing

Header parsing

Status codes

Content-Length handling

Routing

Layered architecture

This mirrors the core of real backend frameworks — without abstraction.


🎯 Learning Goal
The project demonstrates that a web server is fundamentally:
- A TCP loop + a text protocol + a function map
- Frameworks simply automate this.

👤 Author
Linda Eskilsson
Java Developer Student

