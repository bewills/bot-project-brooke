package org.example;

import org.json.JSONArray;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static java.net.http.HttpRequest.*;
import static org.example.Constants.*;

public class ApiRequest {

//    final String userName = "sharedservices";
//    final String passWord = "p@ssword03";
    private final String applicationKey;
//    static String eventsApiURL = "http://ang.nxt.internal/exchange/betting/rest/v1.0/listEventTypes/";

    public ApiRequest (String applicationKey) {
        HttpClient client = HttpClient.newBuilder().build();
        this.applicationKey = applicationKey;

    }

    public HttpRequest createRequest(String apiUrlLogin) {
        return newBuilder()
                .uri(URI.create(apiUrlLogin))
                .header("X-Application", applicationKey)
                .header("Accept", "application/json")
                .POST(BodyPublishers.ofString(""))
                .build();
    }


    public static HttpRequest createEventsRequest(String token, String applicationKey) {
        return newBuilder()
                .uri(URI.create(apiURL + listEvents))
                .header("X-Application", applicationKey)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json" )
                .header("X-Authentication", token)
                .POST(BodyPublishers.ofString("{\"filter\" : { }}"))
                .build();


    }

    public static HttpRequest createCompetitionsRequest (String token, String applicationKey) {
        return newBuilder()
                .uri(URI.create(apiURL + listCompetitions))
                .header("X-Application", applicationKey)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("X-Authentication", token)
                .POST(BodyPublishers.ofString("{\"filter\": {\"name\": \"Soccer\"}}"))
                .build();

   }
    public String callCompetitions (String token, String applicationKey) throws IOException, InterruptedException {

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest createCompetitionsRequest = ApiRequest.createCompetitionsRequest(token, applicationKey);
        HttpResponse<String> response = httpClient.send(createCompetitionsRequest, HttpResponse.BodyHandlers.ofString());
        JSONArray competitionsData = new JSONArray(response.body());

        Competitions.addCompNamesFromJsonResponse(competitionsData);
        return response.body();

    }


}
