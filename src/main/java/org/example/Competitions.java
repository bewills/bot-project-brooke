package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Competitions {
    public static String compId;
    public String compName;

    public static ArrayList<String> compList = new ArrayList<>();
    private static final Map<String, String> compMap = new HashMap<>();

    public Competitions(String compId, String compName) {
        Competitions.compId = compId;
        this.compName = compName;
        compList.add(compName);
        compList.add(compId);
        compMap.put(compName, compId);
    }

    public static void addCompNamesFromJsonResponse(JSONArray competitionsData) {
        if (competitionsData != null && competitionsData.length() > 0) {

            for (int i = 0; i < competitionsData.length(); i++) {
                JSONObject compObject = competitionsData.getJSONObject(i);
                JSONObject compTypeObject = compObject.getJSONObject("competition");
                String compName = compTypeObject.getString("name").toLowerCase().trim();
                String compId = compTypeObject.getString("id");
                compList.add(compName);
                compMap.put(compName, compId);
            }
//            System.out.println(compMap);
        } else {

            System.out.println("No competitions available for today! Please start again");
            System.exit(0);
        }


    }
// use
 public static String getCompId(String userCompChoice) {

     return compMap.getOrDefault(userCompChoice.toLowerCase().trim(), null);
}


}
