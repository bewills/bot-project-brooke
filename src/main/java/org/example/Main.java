package org.example;
import java.util.Scanner;
import java.net.http.HttpRequest;
import java.util.List;

import static org.example.Events.eventsList;
import static org.example.ShowEvents.*;

public class Main {


        public static void main(String[] args) throws Exception {

            final String userName = "sharedservices";
            final String passWord = "p@ssword03";
            final String applicationKey = "npo67wopV4oKVu5g";
            final String apiUrlLogin = "https://identitysso.nxt.com.betfair/api/login?username=" + userName + "&password=" + passWord;
//

//call api request class
            ApiRequest apiRequest = new ApiRequest(applicationKey);
            HttpRequest httpRequest = apiRequest.createRequest(apiUrlLogin);
//call response class
            Response sessionInfo = new Response();
            String responseBody = sessionInfo.retrieveResponse(httpRequest);
            System.out.println(responseBody);

//extract token
            GetToken getToken = new GetToken(responseBody);
            String token = getToken.extractToken();
            System.out.println("Session token is: " + token);
//retrieve events to choose from

            ShowEvents showEvents = new ShowEvents();
            String availableEvents = showEvents.fetchEvents(token, applicationKey);
//            System.out.println(availableEvents);
//            System.out.println(eventsList);
//list competitions
           ApiRequest callCompetitions = new ApiRequest(applicationKey);
           String availableCompetitions = apiRequest.callCompetitions(token, applicationKey);
//           System.out.println(availableCompetitions);
//           System.out.println(Competitions.compList);

           Scanner scanner = new Scanner(System.in);
           System.out.println("Welcome to your Betfair Bot!" +
                    " Please choose a sport to place a bet on from the list below: "
                   + eventsList);
           String userEventChoice = scanner.nextLine();
            boolean isInEventsList = false;
            for (String event : eventsList) {
                if (event.equalsIgnoreCase(userEventChoice)) {
                    isInEventsList = true;
                    break;
                }}

            if (isInEventsList) {
                System.out.println("You selected " + userEventChoice);
                }
                else {
                    System.out.println("Invalid choice. Please choose from the provided list.");
                }
////           System.out.println("You selected " + userEventChoice);
////
////            if userEventChoice:




//
        }}

