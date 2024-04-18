package org.example;

import java.net.http.HttpRequest;

public class Main {


        public static void main(String[] args) throws Exception {

            final String userName = "sharedservices";
            final String passWord = "p@ssword03";
            final String applicationKey = "npo67wopV4oKVu5g";
            final String apiUrl = "https://identitysso.nxt.com.betfair/api/login?username=" + userName + "&password=" + passWord;

//call api request class
            ApiRequest apiRequest = new ApiRequest(applicationKey);
            HttpRequest httpRequest = apiRequest.createRequest(apiUrl);
//call response class
            Response responseHandler = new Response();
            String responseBody = responseHandler.retrieveResponse(httpRequest);
//extract token
            GetToken getToken = new GetToken(responseBody);
            String token = getToken.extractToken();
            System.out.println("Session token is: " + token);
//retrieve events to choose from
//
            ShowEvents showEvents = new ShowEvents();
            showEvents.fetchEvents(token, applicationKey);
            String availableEvents = showEvents.fetchEvents(token, applicationKey);
            System.out.println(availableEvents);
        }}

//logger class - look at library - doesn't give access to system

