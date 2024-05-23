package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Events {

    public String eventId;
    public String eventName;

    public static ArrayList<String> eventList = new ArrayList<>();
    private static Map<String, String> eventMap = new HashMap<>();

    public Events (String eventId, String eventName){

        this.eventId = eventId;
        this.eventName = eventName;
        eventList.add(eventName);
        eventMap.put(eventName, eventId);
    }

    public static void addEventsFromJsonResponse(JSONArray eventsData){
        if (eventsData != null && eventsData.length() > 0) {
            for (int i = 0; i < eventsData.length(); i++){
                JSONObject eventObj = eventsData.getJSONObject(i);
                JSONObject eventObjectDetails = eventObj.getJSONObject("event");
                String eventName = eventObjectDetails.getString("name");
                String eventId = eventObjectDetails.getString("id");
                eventList.add(eventName);

            }

        }

    }
}
