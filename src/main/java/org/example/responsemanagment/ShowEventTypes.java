package org.example.responsemanagment;
import java.io.IOException;

import org.example.responsemanagment.ApiRequest;
import org.example.responsemanagment.EventType;
import org.json.JSONArray;
import org.json.JSONException;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ShowEventTypes {


//    String eventsApiURL = "http://ang.nxt.internal/exchange/betting/rest/v1.0/listEventTypes/";

    public String fetchEventTypes(String token, String applicationKey) throws IOException, InterruptedException {

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest allEventTypeRequest = ApiRequest.createEventTypeRequest(token, applicationKey);


        HttpResponse<String> response = httpClient.send(allEventTypeRequest, HttpResponse.BodyHandlers.ofString());
        String responseBody = response.body();
        System.out.println("Response Body: " + responseBody);

        try {
            JSONArray responseData = new JSONArray(response.body());
            EventType.addEventNamesFromJsonResponse(responseData);
        }
        catch (JSONException e){
            System.err.println("Failed: " + e.getMessage());
        }
        //for me
//        System.out.println("Response Body: " + responseData);
//
//
//
//        EventType.addEventNamesFromJsonResponse(responseData);
//        System.out.println(responseData);
        return response.body();

    }
}


