package org.example;


import org.json.JSONObject;

public class GetToken {
    private final String callResponse;
    //class variable
    public GetToken(String response) {

        this.callResponse = response;
    }
    public String extractToken() {
        //here i am iterating through response in json format and returning the token
        JSONObject jsonResponse = new JSONObject(callResponse);
        //System.out.println(jsonResponse); do i want to retrieve status from this ?
        return jsonResponse.optString("token");

    }
}

//create session object - product id, session token, status of request
//dao class - read