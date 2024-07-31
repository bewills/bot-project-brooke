package org.example;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Locale;


import static java.net.http.HttpRequest.*;
import static org.example.Constants.*;
import static org.example.marketCatalogue.marketId;

public class ApiRequest {

    private final String applicationKey;

    public ApiRequest(String applicationKey) {
        HttpClient client = HttpClient.newBuilder().build();
        this.applicationKey = applicationKey;
    }

    public HttpRequest createRequest(String apiUrlLogin) {
        return newBuilder()
                .uri(URI.create(apiUrlLogin))
                .header("X-Application", applicationKey)
                .header("Accept", "application/json")
                .POST(BodyPublishers.ofString(""))
                .build();
    }

    public static HttpRequest createEventTypeRequest(String token, String applicationKey) {
        return newBuilder()
                .uri(URI.create(apiURL + listEventTypes))
                .header("X-Application", applicationKey)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("X-Authentication", token)
                .POST(BodyPublishers.ofString("{\"filter\" : { }}"))
                .build();

    }

    public static HttpRequest createCompetitionsRequest(String token, String applicationKey, String userEventChoice) {
        String requestCompBody = String.format("{\"filter\": {\"textQuery\": \"%s\"}}", userEventChoice);
        return newBuilder()
                .uri(URI.create(apiURL + listCompetitions))
                .header("X-Application", applicationKey)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("X-Authentication", token)
                .POST(BodyPublishers.ofString(requestCompBody))
                .build();
    }

    public String callCompetitions(String token, String applicationKey, String userEventChoice) throws IOException, InterruptedException {

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest createCompetitionsRequest = ApiRequest.createCompetitionsRequest(token, applicationKey, userEventChoice);
        HttpResponse<String> response = httpClient.send(createCompetitionsRequest, HttpResponse.BodyHandlers.ofString());
        JSONArray competitionsData = new JSONArray(response.body());

        Competitions.addCompNamesFromJsonResponse(competitionsData);
        return response.body();
    }


    public static HttpRequest createEventsRequest(String token, String applicationKey, String userCompChoice) {

        String userCompId = Competitions.getCompId(userCompChoice.toLowerCase().trim());

        String requestEventsBody = String.format("{\"filter\": {\"competitionIds\": [\"%s\"]}}", userCompId);

        return newBuilder()
                .uri(URI.create(apiURL + listEvents))
                .header("X-Application", applicationKey)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("X-Authentication", token)
                .POST(BodyPublishers.ofString(requestEventsBody))
                .build();
    }

    public String callEvents(String token, String applicationKey, String userCompChoice) throws IOException, InterruptedException {

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest createEventsRequest = ApiRequest.createEventsRequest(token, applicationKey, userCompChoice);
        HttpResponse<String> response = httpClient.send(createEventsRequest, HttpResponse.BodyHandlers.ofString());
        JSONArray eventsData = new JSONArray(response.body());

        Events.addEventsFromJsonResponse(eventsData);
        return response.body();
    }

    public static  HttpRequest createMarketCatalogue(String token, String applicationKey, String userCompIdForMkts) {

        String requestMktCatBody = String.format(
                "{\"filter\": {\"competitionIds\": [\"%s\"]}, " +
                        "\"maxResults\": 10, \"inPlayOnly\": true, \"marketProjection\": [\"COMPETITION\", \"EVENT\", \"EVENT_TYPE\", \"RUNNER_DESCRIPTION\"] }",
                userCompIdForMkts
//                "eventIds": ["%s"], "marketIds": ["%s"]
        );

        return newBuilder()
                .uri(URI.create(apiURL + listMarketCatalogue))
                .header("X-Application", applicationKey)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("X-Authentication", token)
                .POST(BodyPublishers.ofString(requestMktCatBody))
                .build();
    }

    public String callMktCat(String token, String applicationKey, String userCompChoice) throws IOException, InterruptedException {
        String userCompIdForMkts = Competitions.getCompId(userCompChoice);

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest createMarketCatalogue = ApiRequest.createMarketCatalogue(token, applicationKey, userCompIdForMkts);
        HttpResponse<String> response = httpClient.send(createMarketCatalogue, HttpResponse.BodyHandlers.ofString());
        JSONArray mktCatData = new JSONArray(response.body());

        marketCatalogue.addMarketNameFromJsonResponse(mktCatData);
        return response.body();
    }


//    public static HttpRequest createMarketBook(String token, String applicationKey, String userMarketId) {
//
//
//        String requestMktBookBody = String.format(
//                "{\"filter\": {\"marketIds\": [\"%s\"]}, \"orderProjection\": \"EXECUTABLE\", \"matchProjection\": \"ROLLED_UP_BY_AVG_PRICE\"}",
//                userMarketId
//        );
//        return newBuilder()
//                .uri(URI.create(apiURL + listMarketBook))
//                .header("X-Application", applicationKey)
//                .header("Accept", "application/json")
//                .header("Content-Type", "application/json")
//                .header("X-Authentication", token)
//                .POST(BodyPublishers.ofString(requestMktBookBody))
//                .build();
//    }
//
//    public String callMktBook(String token, String applicationKey, String userMarketId) throws IOException, InterruptedException {
//
//
//        HttpClient httpClient = HttpClient.newHttpClient();
//        HttpRequest createMarketBook = ApiRequest.createMarketCatalogue(token, applicationKey, userMarketId);
//        HttpResponse<String> response = httpClient.send(createMarketBook, HttpResponse.BodyHandlers.ofString());
//        JSONArray mktBookData = new JSONArray(response.body());
//
//        marketCatalogue.addMarketNameFromJsonResponse(mktBookData);
//        return response.body();
//    }

    public static HttpRequest createOrder(String token, String applicationKey, String userMarketId, String userSelectionId, String betType, double betAmount) {
        String requestOrder = String.format(
                "{\"marketId\": \"%s\", \"instructions\": [{\"selectionId\": \"%s\", \"handicap\": \"0\", \"side\": \"%s\", \"orderType\": \"LIMIT\", \"limitOrder\": {\"size\": %f, \"price\": 2.0, \"persistenceType\": \"LAPSE\"}}]}",
                userMarketId, userSelectionId, betType.toUpperCase(), betAmount);


        return newBuilder()
                .uri(URI.create(apiURL + placeOrders))
                .header("X-Application", applicationKey)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("X-Authentication", token)
                .POST(BodyPublishers.ofString(requestOrder))
                .build();
    }

    public String callPlaceOrder(String token, String applicationKey, String userMarketId, String userSelectionID, double betAmount, String betType) throws IOException, InterruptedException {


        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest createOrder = ApiRequest.createOrder(token, applicationKey, userMarketId, userSelectionID, betType, betAmount);
        HttpResponse<String> response = httpClient.send(createOrder, HttpResponse.BodyHandlers.ofString());
        String responseBody = response.body();
        System.out.println(responseBody);

        JSONArray orderData = new JSONArray(responseBody);
        PlaceOrders.addOrdersFromJsonResponse(orderData);


        return responseBody;
    }
}



//
//
//
//    }
//
//
//
