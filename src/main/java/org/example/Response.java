package org.example;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Response {
    private final HttpClient client;

    public Response() {

        this.client = HttpClient.newBuilder().build();
    }

    public String retrieveResponse(HttpRequest request) throws Exception {
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        //System.out.println(response.body());
        return response.body();
    }
}
//error codes
//if succesful
//response - needs to json string of response and response codes