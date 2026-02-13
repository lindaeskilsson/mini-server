package org.example.index;

import org.example.index.http.HttpParser;
import org.example.index.http.HttpRequest;
import org.example.index.http.HttpResponse;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

    private final int port;
    private static final HttpParser parser = new HttpParser();
    private static final Router router = new Router();

    public SocketServer(int port) {
        this.port = port;

        router.add("GET", "/", req ->
                new HttpResponse(200, "Welcome to Linda's server"));

        router.add("GET", "/health", req ->
                new HttpResponse(200, "OK"));

        router.add("GET", "/favicon.ico", req ->
                new HttpResponse(204, ""));
    }

    public void start() throws IOException {
        ServerSocket server = new ServerSocket(port);
        System.out.println("Server started. Listening to port " + port);

        while (true) {
            Socket client = server.accept();
            System.out.println("Client connected");

            handleClient(client);
        }
    }

        // for each client:
        private static void handleClient(Socket client) {
            try(
                    InputStream in = client.getInputStream();
                    OutputStream out = client.getOutputStream();
                    PrintWriter writer = new PrintWriter(out)
            ){
                // parse request
                HttpRequest request = parser.parse(in);

                // skydd om browser stänger snabbt
                if (request == null) {
                    client.close();
                    return;
                }

                System.out.println(request.getMethod() + " " + request.getPath());

                // 2. Route request
                HttpResponse response = router.route(request);

                // 3. Send response
                writer.print(response.toHttpString());
                writer.flush();


                client.close();}
            catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
