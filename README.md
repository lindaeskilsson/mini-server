Mini HTTP Server (Java)

Detta projekt är en minimal webbserver byggd från grunden med Java sockets.
Syftet är att förstå hur nätverkskommunikation faktiskt fungerar bakom en webbläsare.

Servern binder en port, accepterar klientanslutningar via TCP, läser en HTTP-request och skickar tillbaka ett korrekt formaterat HTTP-svar.

När man öppnar http://localhost:4000 svarar servern med:

Hello Linda, this is your very first own server! congratz!


Projektet demonstrerar praktisk förståelse för:

TCP/IP och sockets

Blocking I/O

Klient–server-kommunikation

HTTP-protokollets struktur

Webben är i grunden bara text över en socket — den här servern visar hur.
