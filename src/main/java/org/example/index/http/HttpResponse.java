package org.example.index.http;

import java.nio.charset.StandardCharsets;

public class HttpResponse {

    private final int statusCode;
    private final String contentType;
    private final byte[] bodyBytes;

    public HttpResponse(int statusCode, String body) {
        this(statusCode, body, "text/plain; charset=UTF-8");
    }

    public HttpResponse(int statusCode, String body, String contentType) {
        this(statusCode, body.getBytes(StandardCharsets.UTF_8), contentType);
    }

    private HttpResponse(int statusCode, byte[] bodyBytes, String contentType) {
        this.statusCode = statusCode;
        this.bodyBytes = bodyBytes;
        this.contentType = contentType;
    }

    public String toHttpString() {
        String statusText = switch (statusCode) {
            case 200 -> "OK";
            case 204 -> "No Content";
            case 404 -> "Not Found";
            case 500 -> "Internal Server Error";
            default -> "";
        };

        return "HTTP/1.1 " + statusCode + " " + statusText + "\r\n" +
                "Content-Type: " + contentType + "\r\n" +
                "Content-Length: " + bodyBytes.length + "\r\n" +
                "Connection: close\r\n" +
                "\r\n";
    }
    // ✅ Den du saknar
    public static HttpResponse bytes(int statusCode, byte[] bodyBytes, String contentType) {
        return new HttpResponse(statusCode, bodyBytes, contentType);
    }

    // ✅ Den du saknar
    public byte[] getBodyBytes() {
        return bodyBytes;
    }


}
