package org.example.responsemanagment;

import org.json.JSONArray;
import org.json.JSONObject;

public class PlaceOrders {

    public String status;

    public void placeOrders(String orderStatus){
        this.status = orderStatus;
    }

    public static void addOrdersFromJsonResponse(JSONArray orderData) {
        if (orderData != null && orderData.length() > 0) {

            for (int i = 0; i < orderData.length(); i++) {
                JSONObject orderObject = orderData.getJSONObject(i);
                String orderStatus = orderObject.getString("status").toLowerCase().trim();
                System.out.println(orderStatus);

           }
        } else {

            System.out.println("Order not successful");
            System.exit(0);
        }


        }

}