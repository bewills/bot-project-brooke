package org.example.responsemanagment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;
import java.util.Map;


public class marketCatalogue {

   public String selectionId;
    public static String marketId;
    public String marketName;
    public String runnerName;

    public static ArrayList<String> mktCatList = new ArrayList<>();
    public static Map<String, String> mktCatMap = new HashMap<>();
    public static Map<String, String> runnerMap = new HashMap<>();
    public static Map<String, List<String>> marketRunnersMap = new HashMap<>();
    public static Map<String, String> runnerNameToSelectionIdMap = new HashMap<>();


    public marketCatalogue (String marketId, String marketName, String selectionId, String runnerName) {
        this.marketId = marketId;
        this.marketName = marketName;
        this.selectionId = selectionId;
        this.runnerName = runnerName;
        mktCatList.add(marketId);
        mktCatMap.put(marketName, marketId);
        if (selectionId != null) {
            runnerMap.put(selectionId, runnerName);
            marketRunnersMap.putIfAbsent(marketId, new ArrayList<>());
            marketRunnersMap.get(marketId).add(runnerName);
            runnerNameToSelectionIdMap.put(runnerName, selectionId);

        }
    }

    public static void addMarketNameFromJsonResponse(JSONArray mktCatData) {
        if (mktCatData != null && mktCatData.length() > 0) {
            for (int i = 0; i < mktCatData.length(); i++) {
                JSONObject mktCatObject = mktCatData.getJSONObject(i);
                JSONObject mktCatTypeObject = mktCatObject.getJSONObject("event");
                JSONArray runners = mktCatObject.optJSONArray("runners");
                String marketName = mktCatObject.getString("marketName").toLowerCase().trim();
                String marketId = mktCatObject.getString("marketId");
                mktCatList.add(marketName);
                mktCatMap.put(marketName, marketId);

                marketRunnersMap.putIfAbsent(marketId, new ArrayList<>());


                if (runners != null) {
                    for (int j = 0; j < runners.length(); j++) {
                        JSONObject runnerObject = runners.getJSONObject(j);
                        String runnerName = runnerObject.getString("runnerName").toLowerCase().trim();
                        int selectionId = runnerObject.getInt("selectionId");

                        runnerMap.put(String.valueOf(selectionId), runnerName);
                        marketRunnersMap.get(marketId).add(runnerName);
                        runnerNameToSelectionIdMap.put(runnerName, String.valueOf(selectionId));
                    }
                }
            }
        } else {
            System.out.println("No info available for today!");

        }
    }

    public static List<String> getRunnersForMarketId(String userMarketId) {
        return marketRunnersMap.getOrDefault(userMarketId, new ArrayList<>());
    }
    public static String getSelectionIdForRunner(String runnerName) {
        return runnerNameToSelectionIdMap.get(runnerName);
    }

}