package org.example;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

import static org.example.Constants.*;

public class ApiRequest {

//    final String userName = "sharedservices";
//    final String passWord = "p@ssword03";
    private final String applicationKey;
    static String eventsApiURL = "http://ang.nxt.internal/exchange/betting/rest/v1.0/listEventTypes/";

    public ApiRequest(String applicationKey) {
        HttpClient client = HttpClient.newBuilder().build();
        this.applicationKey = applicationKey;

    }

    public HttpRequest createRequest(String apiUrlLogin) {
        return HttpRequest.newBuilder()
                .uri(URI.create(apiUrlLogin))
                .header("X-Application", applicationKey)
                .header("Accept", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(""))
                .build();
    }





    public static HttpRequest createEventsRequest(String token, String applicationKey) {
        return HttpRequest.newBuilder()
                .uri(URI.create(apiURL + listEvents))
                .header("X-Application", applicationKey)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json" )
                .header("X-Authentication", token)
                .POST(HttpRequest.BodyPublishers.ofString("{\"filter\" : { }}"))
                .build();


    }

//    public static HttpRequest callCompetitions (String token, String applicationKey) {
//        return HttpRequest.newBuilder()
//                .uri(URI.create(apiURL + listCompetitions))
//                .header("X-Application", applicationKey)
//                .header("Accept", "application/json")
//                .header("Content-Type", "application/json")
//                .header("X-Authentication", token)
//                .POST(HttpRequest.BodyPublishers.ofString("{\"filter\": {\"name\": \"Soccer\"}}")
//                .build();
//
//        HttpClient httpClient = HttpClient.newHttpClient();
//        HttpRequest callCompetitions = ApiRequest.callCompetitions(token, applicationKey);
//
//
//    }

}
