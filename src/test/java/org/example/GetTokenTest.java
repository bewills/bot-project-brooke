package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GetTokenTest {

    @Test
    public void extractToken() {

        String jsonResponseString = "{\"token\":\"example_token\"}";
        GetToken getToken = new GetToken(jsonResponseString);

        String extractedToken = getToken.extractToken();

        assertEquals("example_token", extractedToken);
        assertNotNull(extractedToken);


    }
}