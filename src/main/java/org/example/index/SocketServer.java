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

        router.add("GET", "/app.js", req -> {
            byte[] file = StaticFileService.read("/app.js");
            if (file == null) return new HttpResponse(404, "Not found: /app.js");
            return HttpResponse.bytes(200, file, "application/javascript; charset=UTF-8");
        });

        router.add("GET", "/style.css", req -> {
            byte[] file = StaticFileService.read("/Style.css");
            if (file == null) return new HttpResponse(404, "Not found: /style.css");
            return HttpResponse.bytes(200, file, "text/css; charset=UTF-8");
        });


        router.add("GET", "/", req -> {
            byte[] file = StaticFileService.read("/index.html");
            if (file == null) return new HttpResponse(404, "Not found: /index.html");
            return HttpResponse.bytes(200, file, "text/html; charset=UTF-8");
        });

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

    private static void handleClient(Socket client) {
        try (
                InputStream in = client.getInputStream();
                OutputStream out = client.getOutputStream();
                PrintWriter writer = new PrintWriter(out)
        ) {
            HttpRequest request = parser.parse(in);

            if (request == null) {
                client.close();
                return;
            }

            System.out.println(request.getMethod() + " " + request.getPath());

            HttpResponse response = router.route(request);

            // 1) skriv headers
            writer.print(response.toHttpString());
            writer.flush();

            // 2) skriv body bytes (superviktigt för css/js)
            out.write(response.getBodyBytes());
            out.flush();

            client.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String guessContentType(String path) {
        if (path.endsWith(".css")) return "text/css; charset=UTF-8";
        if (path.endsWith(".js")) return "application/javascript; charset=UTF-8";
        if (path.endsWith(".html")) return "text/html; charset=UTF-8";
        if (path.endsWith(".json")) return "application/json; charset=UTF-8";
        if (path.endsWith(".png")) return "image/png";
        if (path.endsWith(".jpg") || path.endsWith(".jpeg")) return "image/jpeg";
        if (path.endsWith(".svg")) return "image/svg+xml";
        return "text/plain; charset=UTF-8";
    }
}
