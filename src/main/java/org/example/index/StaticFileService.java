package org.example.index;

import java.io.IOException;
import java.io.InputStream;

public class StaticFileService {

    public static byte[] read(String path) {
        String resourcePath = "/static" + path;

        try (InputStream in = StaticFileService.class.getResourceAsStream(resourcePath)) {
            if (in == null) return null;
            return in.readAllBytes();
        } catch (IOException e) {
            return null;
        }
    }
}