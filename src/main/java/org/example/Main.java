package org.example;
import java.util.Scanner;
import java.net.http.HttpRequest;
import java.util.List;

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
            //System.out.println(responseBody);

//extract token
            GetToken getToken = new GetToken(responseBody);
            String token = getToken.extractToken();
            System.out.println("Session token is: " + token);

//retrieve events to choose from
//
            ShowEvents showEvents = new ShowEvents();
            String availableEvents = showEvents.fetchEvents(token, applicationKey);
//            System.out.println(availableEvents);
            System.out.println(Events.eventsList);
//list competitions
           ApiRequest callCompetitions = new ApiRequest(applicationKey);
           String availableCompetitions = apiRequest.callCompetitions(token, applicationKey);
           System.out.println(availableCompetitions);
           System.out.println(Competitions.compList);




//
        }}

//IT IS NOT PASSING THE TOKEN INTO FETCH EVENTS

// questions for reeja - is it because of different link for log in and events list api call ?