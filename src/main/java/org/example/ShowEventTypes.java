package org.example;
import java.io.IOException;

import org.json.JSONArray;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ShowEventTypes {


//    String eventsApiURL = "http://ang.nxt.internal/exchange/betting/rest/v1.0/listEventTypes/";

    public String fetchEventTypes(String token, String applicationKey) throws IOException, InterruptedException {

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest allEventTypeRequest = ApiRequest.createEventTypeRequest(token, applicationKey);


        HttpResponse<String> response = httpClient.send(allEventTypeRequest, HttpResponse.BodyHandlers.ofString());
        JSONArray responseData = new JSONArray(response.body());

        EventType.addEventNamesFromJsonResponse(responseData);
        System.out.println(responseData);
        return response.body();

    }
}


