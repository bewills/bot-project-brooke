package org.example.responsemanagment;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MarketBook {


    public static void addMOrderDataFromJsonResponse(JSONArray orderData) {
        if (orderData != null && orderData.length() > 0) {
            for (int i = 0; i < orderData.length(); i++) {
                JSONObject orderObject = orderData.getJSONObject(i);
                System.out.println(orderObject);
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

