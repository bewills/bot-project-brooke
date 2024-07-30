package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class EventType {
    public String eventId;
    public String eventName;

    public static ArrayList<String> eventTypeList = new ArrayList<>();

    public EventType (String eventTypeName, String eventTypeId) {
       this.eventId = eventTypeId;
       this.eventName = eventTypeName;
       eventTypeList.add(eventName);
    }
    public static void addEventNamesFromJsonResponse(JSONArray responseData) {
        {
            if (responseData != null && responseData.length() > 0) {
                for (int i = 0; i < responseData.length(); i++) {
                    JSONObject eventObject = responseData.getJSONObject(i);
                    JSONObject eventTypeObject = eventObject.getJSONObject("eventType");
                    String eventTypeName = eventTypeObject.getString("name");
                    eventTypeList.add(eventTypeName);
                }
            } else {
                // Handle the case when the JSONArray is null or empty
                System.out.println("No data available.");
            }

        }
    }}

