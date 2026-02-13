package org.example.index.http;

import org.example.index.SocketServer;

import java.io.IOException;


public class MyServer {
    static void main() throws IOException {
        SocketServer server = new SocketServer(4000);
        server.start();

    }
}
