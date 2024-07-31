package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

public class PlaceOrders {

    public String status;

    public void placeOrders (String status){

        this.status = status;
    }
    public static void addOrdersFromJsonResponse(JSONArray orderData) {
        if (orderData != null && orderData.length() > 0) {

            for (int i = 0; i < orderData.length(); i++) {
                JSONObject orderObject = orderData.getJSONObject(i);
                String status = orderObject.getString("name").toLowerCase().trim();
           }
        } else {

            System.out.println("Order not successful");
            System.exit(0);
        }
}}