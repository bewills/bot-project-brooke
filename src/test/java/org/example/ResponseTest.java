package org.example;

import org.example.responsemanagment.Response;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.http.HttpRequest;

import static org.junit.jupiter.api.Assertions.*;

class ResponseTest {

    @Test
    public void retrieveResponse() throws Exception {

        final String userName = "sharedservices";
        final String passWord = "p@ssword03";
        final String apiUrl = "https://identitysso.nxt.com.betfair/api/login?username=" + userName + "&password=" + passWord;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .GET()
                .build();
        Response responseHandler = new Response();

        String responseBody = responseHandler.retrieveResponse(request);

        assertNotNull(responseBody);
// checks that the value is not null and will throw exception if not correct response body? - ask reeja
    }
}