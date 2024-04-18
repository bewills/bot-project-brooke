package org.example;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

public class ApiRequest {

    private final String applicationKey;

    public ApiRequest(String applicationKey){
        HttpClient client = HttpClient.newBuilder().build();
        this.applicationKey = applicationKey;

    }

    public HttpRequest createRequest(String apiUrl) {
        return HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .header("X-Application", applicationKey)
                .header("Accept", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(""))
                .build();
}}
