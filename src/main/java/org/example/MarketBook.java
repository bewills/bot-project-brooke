package org.example;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MarketBook {

//    public static ArrayList<String> mktCatList = new ArrayList<>();
//    //    public static ArrayList<String> selectionIdList = new ArrayList<>();
//    public static Map<String, String> mktCatMap = new HashMap<>();
//
//    public void marketBook (String price, String marketName, String selectionId) {
//        this.marketId = marketId;
//        this.marketName = marketName;
//        this.selectionId = selectionId;
//        mktCatList.add(marketId);
////        selectionIdList.add(selectionId);
//        mktCatMap.put(marketName, marketId);
//    }

    public static void addMarketDataFromJsonResponse(JSONArray mktBookData) {
        if (mktBookData != null && mktBookData.length() > 0) {
            for (int i = 0; i < mktBookData.length(); i++) {
                JSONObject mktBookObject = mktBookData.getJSONObject(i);
//                JSONObject mktCatTypeObject = mktCatObject.getJSONObject("event");
//                String marketName = mktCatObject.getString("marketName").toLowerCase().trim();
//                String marketId = mktCatObject.getString("marketId");
////                String selectionId = mktCatObject.getString("selectionId");
//                mktCatList.add(marketName);
//                mktCatMap.put(marketName, marketId);
////                selectionIdList.add(selectionId);
            }
        } else {
            System.out.println("No info available for today!");
        }
    }
}

