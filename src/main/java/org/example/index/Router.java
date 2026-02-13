package org.example.index;

import org.example.index.http.HttpRequest;
import org.example.index.http.HttpResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Router {

    private final Map<String, Function<HttpRequest, HttpResponse>> routes = new HashMap<>();

    public void add(String method, String path, Function<HttpRequest, HttpResponse> handler) {
        routes.put(key(method, path), handler);
    }

    public HttpResponse route(HttpRequest req) {
        Function<HttpRequest, HttpResponse> handler = routes.get(key(req.getMethod(), req.getPath()));

        if (handler == null) {
            return new HttpResponse(404, "Not Found: " + req.getPath());
        }

        return handler.apply(req);
    }

    private String key(String method, String path) {
        return method + " " + path;
    }
}
