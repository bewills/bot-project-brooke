package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Competitions {
        public String compId;
        public String compName;

        public static ArrayList<String> compList = new ArrayList<>();

        public Competitions(String compId, String compName) {
            this.compId = compId;
            this.compName = compName;
            compList.add(compName);
            System.out.println(compList);
        }
     public static void addCompNamesFromJsonResponse(JSONArray competitionsData) {
         for (int i = 0; i < competitionsData.length(); i++) {
             JSONObject compObject = competitionsData.getJSONObject(i);
             JSONObject compTypeObject = compObject.getJSONObject("competition");
             String eventName = compTypeObject.getString("name");
             compList.add(eventName);
         }
     }}