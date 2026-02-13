package org.example.index.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class HttpParser {

    public HttpRequest parse(InputStream in) throws IOException {
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));

        // 1) Läs request line: "GET /hej HTTP/1.1"
        String requestLine = reader.readLine();
        if (requestLine == null || requestLine.isBlank()) {
            return null; // ingen request (eller tom)
        }

        // 2) split på mellanslag → method + path
        String[] parts = requestLine.split(" ");
        String method = parts[0];
        String path = parts[1];

        // 3) Läs headers rad för rad tills tom rad
        Map<String, String> headers = new HashMap<>();
        String line;
        while ((line = reader.readLine()) != null && !line.isEmpty()) {

            // 4) split på första ":" → key/value
            int colon = line.indexOf(':');
            if (colon == -1) continue; // skydd om konstig rad

            String key = line.substring(0, colon).trim();
            String value = line.substring(colon + 1).trim();
            headers.put(key, value);
        }

        return new HttpRequest(method, path, headers);
    }
}
