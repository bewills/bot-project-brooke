package org.example;

import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.http.HttpRequest;

import static org.junit.jupiter.api.Assertions.*;
class ApiRequestTest {

    @Test
    public void createRequest() {

        final String userName = "sharedservices";
        final String passWord = "p@ssword03";
        String applicationKey = "your-application-key";
        String apiUrl = "https://identitysso.nxt.com.betfair/api/login?username=" + userName + "&password=" + passWord;
        ApiRequest apiRequest = new ApiRequest(applicationKey);

        HttpRequest request = apiRequest.createRequest(apiUrl);

        assertNotNull(request);
        // correct URL
        assertEquals(URI.create(apiUrl), request.uri());

        // correct headers
        assertEquals(applicationKey, request.headers().firstValue("X-Application").orElse(null));;
        assertNotNull(request.headers().firstValue("X-Application"));
    }
}
