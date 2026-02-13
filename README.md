Mini HTTP Server (Java)

En minimal webbserver byggd från grunden i Java utan ramverk.
Projektet demonstrerar hur HTTP fungerar ovanpå TCP — från socket-anslutning till routing och respons.

Syftet är att förstå vad som faktiskt händer bakom t.ex. Spring Boot / Express.

Funktioner

Tar emot riktiga HTTP-requests från webbläsare

Parser för:

method

path

headers

Router med handlers

Dynamiska responses

Automatisk 404-hantering

Separerad arkitektur (transport vs applikationslogik)

Arkitektur
Browser
   ↓
SocketServer (TCP)
   ↓
HttpParser (tolkar request)
   ↓
Router (väljer handler)
   ↓
HttpResponse (bygger HTTP-svar)
   ↓
Browser


Servern är uppdelad i lager:

Lager	Ansvar
SocketServer	Hanterar TCP-anslutningar
HttpParser	Omvandlar text → HttpRequest
Router	Bestämmer vad som ska köras
HttpResponse	Bygger korrekt HTTP-svar
Exempel-routes
URL	Resultat
/	Welcome-text
/health	OK
okänd path	404 Not Found
Starta servern
1. Klona repo
git clone https://github.com/lindaeskilsson/mini-server.git
cd mini-server

2. Kör

(antingen via IDE eller terminal)

mvn compile
mvn exec:java -Dexec.mainClass="org.example.index.http.MyServer"


Servern startar på:

http://localhost:4000

Testa

I webbläsare:

http://localhost:4000/
http://localhost:4000/health
http://localhost:4000/anything


eller via curl:

curl -v http://localhost:4000/health

Vad projektet visar

Projektet implementerar grunderna i HTTP:

request line parsing

headers

status codes

content length

routing

separation of concerns

Detta motsvarar kärnan i riktiga webbframeworks — men utan abstraktioner.

Framtida förbättringar

POST body parsing

Query parameters

JSON responses

Threading / concurrent clients

Middleware / filter chain

Static files

Lärdom

Projektet visar att en webbserver i grunden bara är:

en TCP-loop + texttolkning + funktionskarta
