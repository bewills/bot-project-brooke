package org.example;

import java.net.http.HttpRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import static org.example.Competitions.*;
import static org.example.EventType.eventTypeList;
import static org.example.Events.*;
import static org.example.marketCatalogue.*;

public class Main {
    public static final String userName = "sharedservices";
    public static final String passWord = "p@ssword03";
    public static final String applicationKey = "npo67wopV4oKVu5g";
    public static final String apiUrlLogin = "https://identitysso.nxt.com.betfair/api/login?username=" + userName + "&password=" + passWord;
//

    public static void main(String[] args) throws Exception {

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

        Set<String> eventTypeSet = new HashSet<>();
        for (String eventType : eventTypeList) {
            eventTypeSet.add(eventType.trim().toLowerCase());
        }
//user decides on available event type
        String userEventTypeChoice;

        while (true) {
            userEventTypeChoice = scanner.nextLine().toLowerCase().trim();
            if (eventTypeSet.contains(userEventTypeChoice.toLowerCase())) {
                System.out.println("You selected " + userEventTypeChoice);
                break;
            } else {
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
// user decides on available competitions
        String userCompChoice;
        String userCompIdForMkts = null;
        while (true) {
            userCompChoice = scanner.nextLine().toLowerCase().trim();
            if (compSet.contains(userCompChoice.toLowerCase())) {
                System.out.println("You selected " + userCompChoice);
                userCompIdForMkts = getCompId(userCompChoice);
                break;
            }
              else {
                System.out.println("Invalid choice. Please choose from the provided list.");
            }
        }

        if (userCompIdForMkts!=null) {
            System.out.println(userCompIdForMkts);
            System.out.println("The following events are taking place, please choose one: ");
            String availableEvents = apiRequest.callEvents(token, applicationKey, userCompChoice);
            System.out.println(eventList);

            Set<String> eventSet = new HashSet<>();
            for (String event : eventList) {
                eventSet.add(event.trim().toLowerCase());
            }

            String userEventChoice;
            String userEventID = null;
            while (true) {
                userEventChoice = scanner.nextLine().toLowerCase().trim();
                if (eventSet.contains(userEventChoice.toLowerCase())) {
                    System.out.println("You selected " + userEventChoice);
                    userEventID = Events.getEventId(userEventChoice);
                    System.out.println(userEventID); //here are the changes
                    break;
                } else {
                    System.out.println("Invalid choice. Please choose from the provided list.");
                }
              }

//user decides a market
                    System.out.println("The following markets are available, please choose one: ");
                    String userMarketId = null;
                    String availableMarkets = apiRequest.callMktCat(token, applicationKey, userCompChoice);//taken userCompChoice out temporarily
                    System.out.println(mktCatList);
                    System.out.println(">");


                Set<String> mktCatSet = new HashSet<>();
                for (String event : mktCatList) {
                    mktCatSet.add(event.trim().toLowerCase());

                    String userMarketChoice;
                    while (true) {
                        userMarketChoice = scanner.nextLine().toLowerCase().trim();
                        if (mktCatSet.contains(userMarketChoice.toLowerCase())) {
//                        System.out.println("You selected " + userMarketChoice);
                            if (mktCatMap.containsKey(userMarketChoice)) {
                                userMarketId = mktCatMap.get(userMarketChoice);

                            }

                            System.out.println("You selected " + userMarketChoice + " and the market id is: " + userMarketId);
//                            System.out.println(marketRunnersMap);
                            List<String> runners = getRunnersForMarketId(userMarketId);
                            System.out.println(runners);
                            if (runners.isEmpty()) {
                                System.out.println("No runners found for this market.");
                            } else {
//user chooses a team/player
                                System.out.println("Please choose a runner to place your bet on: ");
                                for (String runnerName : runners) {
                                    System.out.println("Runner Name: " + runnerName);
                                }
                                String runnerChoice = scanner.nextLine().toLowerCase().trim();
                                String userSelectionId = getSelectionIdForRunner(runnerChoice);
                                String betType = null;
                                double betAmount = 0;
                                if (userSelectionId != null) {
                                    System.out.println("Selection ID for runner " + runnerChoice + " is: " + userSelectionId);
//user chooses bet type and amount
                                    System.out.println("Would you like to place a 'lay' or 'back' bet? ");
                                    betType = scanner.nextLine().toLowerCase().trim();
                                    while (!betType.equals("lay") && !betType.equals("back")) {
                                        System.out.println("Invalid choice. Please enter 'lay' or 'back': ");
                                        betType = scanner.nextLine().toUpperCase().trim();
                                    }
                                    System.out.println("How much would you like to bet? ");
                                    while (true) {
                                        try {
                                            betAmount = Double.parseDouble(scanner.nextLine().trim());
                                            if (betAmount <= 0) {
                                                System.out.println("Please enter a positive amount.");
                                            } else {
                                                break;
                                            }
                                        } catch (NumberFormatException e) {
                                            System.out.println("Invalid amount. Please enter a number.");
                                        }
                                    }

                                    System.out.println("You are about to place a " + betType + " bet of " + betAmount + " on runner " +
                                            runnerChoice );
                                    String orderInfo = apiRequest.callPlaceOrder(token, applicationKey, userMarketId, userSelectionId, betAmount, betType);
                                    System.out.println(orderInfo);
                                } else {
                                    System.out.println("Selection ID not found for runner " + runnerChoice);
                                }
                            }
                        } else {
                            System.out.println("No runners found for this market.");
                        }
                    }

//     }  else {
//                System.out.println("Invalid choice. Please choose from the provided list.");
//            }
                }
            }
        }
}




















