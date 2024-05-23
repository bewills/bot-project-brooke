package org.example;

import java.net.http.HttpRequest;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import static org.example.Competitions.*;
import static org.example.EventType.eventTypeList;
import static org.example.EventType.eventTypeList;
import static org.example.Events.eventList;

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


//extract token
        GetToken getToken = new GetToken(responseBody);
        String token = getToken.extractToken();
        System.out.println("Session token is: " + token); // user logger here
//retrieve events to choose from

        ShowEventTypes showEventTypes = new ShowEventTypes();
        String availableEventTypes = showEventTypes.fetchEventTypes(token, applicationKey);


////user input starts
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to your Betfair Bot! Please choose a sport to place a bet on from the list below: " + eventTypeList);

        Set<String> eventsSet = new HashSet<>();
        for (String event : eventTypeList) {
            eventsSet.add(event.trim().toLowerCase());
        }

        String userEventTypeChoice;

        while (true) {
            userEventTypeChoice = scanner.nextLine().toLowerCase().trim();
            if (eventsSet.contains(userEventTypeChoice.toLowerCase()))
            {
                System.out.println("You selected " + userEventTypeChoice);
                break;
            }
            else {
                System.out.println("Invalid choice. Please choose from the provided list.");
            }
        }

        System.out.println("Please select a competition to place a bet on: ");
        String availableCompetitions = apiRequest.callCompetitions(token, applicationKey, userEventTypeChoice);
        System.out.println(compList);



        Set<String> compSet = new HashSet<>();
        for (String comp : compList) {
            compSet.add(comp.trim().toLowerCase());
        }

        String userCompChoice;


        while (true) {
            userCompChoice = scanner.nextLine().toLowerCase().trim();
            if (compSet.contains(userCompChoice.toLowerCase())) {
                System.out.println("You selected " + userCompChoice);
//                String userCompId = getCompId(userCompChoice.toLowerCase().trim());
//                System.out.println(userCompId); // test to see if retrieving id
                break;
            } else {
                System.out.println("Invalid choice. Please choose from the provided list.");
            }
        }
        System.out.println("The following events are taking place, please choose one: ");
        String availableEvents = apiRequest.callEvents(token, applicationKey, userCompChoice);
        System.out.println(eventList);

    }
}

















//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Welcome to your Betfair Bot!" +
//                " Please choose a sport to place a bet on from the list below: "
//                + eventsList);
//        String userEventChoice = scanner.nextLine();
//        boolean isInEventsList = false;
//        for (String event : eventsList) {
//            if (event.equalsIgnoreCase(userEventChoice)) {
//                isInEventsList = true;
//                break;
//            }
//        }
//
//        if (isInEventsList) {
//            System.out.println("You selected " + userEventChoice);
//            Scanner compScanner = new Scanner(System.in);
//            System.out.println("Please select a competition to place a bet on: ");
//            String availableCompetitions = apiRequest.callCompetitions(token, applicationKey, userEventChoice);
//            System.out.println(compList);
//            boolen isInCompList = false;
//            for (String comp: compList);
//            String userCompChoice = compScanner.nextLine();
//        } else {
//            System.out.println("Invalid choice. Please choose from the provided list.");
//
//        }
//    }
//
//}

