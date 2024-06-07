package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class marketCatalogue {

    public String marketId;
    public String marketName;

    public static ArrayList<String> mktCatList = new ArrayList<>();
    private static final Map<String, String> mktCatMap = new HashMap<>();

    public marketCatalogue (String marketId, String marketName) {
        this.marketId = marketId;
        this.marketName = marketName;
        mktCatList.add(marketId);
        mktCatMap.put(marketId,marketName);
    }

    public static void addMarketNameFromJsonResponse(JSONArray mktCatData) {
        if (mktCatData != null && mktCatData.length() > 0) {
            for (int i = 0; i < mktCatData.length(); i++) {
                JSONObject mktCatObject = mktCatData.getJSONObject(i);
                JSONObject mktCatTypeObject = mktCatObject.getJSONObject("event");
                String marketName = mktCatObject.getString("marketName").toLowerCase().trim();
                String marketId = mktCatObject.getString("marketId");
                mktCatList.add(marketName);
                mktCatMap.put(marketName, marketId);
            }
        } else {
            System.out.println("No info available for today!");
        }
    }
}