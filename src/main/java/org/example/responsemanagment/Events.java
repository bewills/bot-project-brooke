package org.example.responsemanagment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Events {

    public static String eventId;
    public static String eventName;

    public static ArrayList<String> eventList = new ArrayList<>();
    public static Map<String, String> eventsMap = new HashMap<>();

//    public Events (String eventId, String eventName){
//
//        this.eventId = eventId;
//        this.eventName = eventName;
//        eventList.add(eventName);
//        eventsMap.put(eventName.toLowerCase().trim(), eventId);
//    }

    public static void addEventsFromJsonResponse(JSONArray eventsData){
        if (eventsData != null && eventsData.length() > 0) {
            for (int i = 0; i < eventsData.length(); i++){
                JSONObject eventObj = eventsData.getJSONObject(i);
                JSONObject eventObjectDetails = eventObj.getJSONObject("event");
                String eventName = eventObjectDetails.getString("name");
                String eventId = eventObjectDetails.getString("id");
                eventList.add(eventName);
                eventsMap.put(eventName.toLowerCase().trim(), eventId);

            }

        }

    }
//    public static String getEventId (String userEventChoice) {
//        return eventMap.get(userEventChoice.toLowerCase().trim());
//    }

    public static String getEventId(String userEventChoice) {
        return eventsMap.getOrDefault(userEventChoice.toLowerCase().trim(), null);
    }

}

