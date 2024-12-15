package org.example.asserts;

import io.restassured.response.Response;

import static org.testng.Assert.assertEquals;

public class AssertActions {

    // common assertions

    public static void verifyResponseBody(String actual,String expected, String description){
        assertEquals(actual,expected,description);
    }

    public static void verifyResponseBody(String actual,int expected, String description){
        assertEquals(actual,expected,description);
    }

    public static void verifyStatusCode(Response response,Integer expected){
        assertEquals(response.getStatusCode(),expected);

    }
}
