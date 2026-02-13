package org.example.index.http;

public class HttpResponse {

    private final int statusCode;
    private final String body;

    public HttpResponse(int statusCode, String body){
        this.statusCode = statusCode;
        this.body = body;
    }

    public String toHttpString(){
        String statusText = switch (statusCode){
            case 200 -> "OK";
            case 202 -> "Accepted";
            case 404 -> "Not Found";
            case 500 -> "Internal Server Error";
            default -> "";
        };
        return "HTTP/1.1 " + statusCode + " " + statusText + " " + "\r\n"
                + "Content-Type: text/plain\r\n"
                + "Content-Length: " + body.length() + "\r\n"
                + "\r\n" +
                body;
     }

}
