package org.example.index;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {
     static void main() throws IOException {

        System.out.println("Starting server.....");

        // Starta server (bind port: 4000)
        ServerSocket server = new ServerSocket(4000);
        System.out.println("Server started. Listening to port 4000");

        // While true -> accept clients
        while (true){
            Socket client = server.accept();
            System.out.println("Client connected");
            handleClient(client);
        }
        //respond
        // Close socket
    }

    // for each client:
    private static void handleClient(Socket client) {
        try(
                InputStream in = client.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                OutputStream out = client.getOutputStream();
                PrintWriter writer = new PrintWriter(out)
                ){
            //Read message, inputStream
            String line;
            while ((line = reader.readLine()) != null && !line.isEmpty()) {
                System.out.println(line);
            }
            //respond
            writer.print("HTTP/1.1 200 OK\r\n");
            writer.print("Content-Type: text/plain\r\n");
            writer.print("\r\n");
            writer.print("Hello Linda, this is your very first own server! congratz!");
            writer.flush();

            client.close();}
        catch (IOException e) {
            e.printStackTrace();
        }

        }
}
