package org.example;


import org.json.JSONObject;

public class GetToken {
    private final String callResponse;
    public GetToken(String response) {
        this.callResponse = response;
    }
    public String extractToken() {

        JSONObject jsonResponse = new JSONObject(callResponse);


        return jsonResponse.optString("token");

    }
}