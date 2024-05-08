package org.example;
import java.io.IOException;
import java.util.*;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;






public class ShowEvents {


    String eventsApiURL = "http://ang.nxt.internal/exchange/betting/rest/v1.0/listEventTypes/";

    public String fetchEvents(String token, String applicationKey) throws IOException, InterruptedException {

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest allEventsRequest = ApiRequest.createEventsRequest(token, applicationKey);


        HttpResponse<String> response = httpClient.send(allEventsRequest, HttpResponse.BodyHandlers.ofString());
        JSONArray responseData = new JSONArray(response.body());
//        for (int i = 0; i <responseData.length(); i++) {
//           Object eventNames= responseData.get(i);
//            System.out.println("Event Type: " + eventNames.toString());

        Events.addEventNamesFromJsonResponse(responseData);
        return response.body();

    }
}
//
//}
//

