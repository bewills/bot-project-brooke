package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Events {
    public String eventId;
    public String eventName;

    public static ArrayList<String> eventsList = new ArrayList<>();

    public Events(String eventName, String eventId) {
       this.eventId = eventId;
       this.eventName = eventName;
       eventsList.add(eventName);
    }
    public static void addEventNamesFromJsonResponse(JSONArray responseData) {
        {
            if (responseData != null && responseData.length() > 0) {
                for (int i = 0; i < responseData.length(); i++) {
                    JSONObject eventObject = responseData.getJSONObject(i);
                    JSONObject eventTypeObject = eventObject.getJSONObject("eventType");
                    String eventName = eventTypeObject.getString("name");
                    eventsList.add(eventName);
                }
            } else {
                // Handle the case when the JSONArray is null or empty
                System.out.println("No competitions data available.");
            }

        }
    }}

//LOMBOK