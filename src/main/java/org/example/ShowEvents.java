package org.example;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONObject;



public class ShowEvents {

    String eventsApiURL = "http://ang.nxt.internal/exchange/betting/rest/v1.0/listEventTypes/";



    public HttpRequest createEventsRequest(String apiUrl, String token, String applicationKey) {
        return HttpRequest.newBuilder()
                .uri(URI.create(eventsApiURL))
                .header("X-Application", applicationKey)
                .header("Accept", "application/json")
                .header("X-Authentication", token)
                .POST(HttpRequest.BodyPublishers.ofString("{\"filter\" : { }}"))
                .build();
    }
    public String fetchEvents(String token, String applicationKey) throws IOException, InterruptedException {

        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest allEventsRequest = createEventsRequest(eventsApiURL, applicationKey, token);
        HttpResponse<String> response = httpClient.send(allEventsRequest, HttpResponse.BodyHandlers.ofString());
        return response.body();

}
}
