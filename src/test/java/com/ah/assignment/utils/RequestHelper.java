package com.ah.assignment.utils;



import com.ah.assignment.testBase.TestContext;

import static io.restassured.RestAssured.given;

public class RequestHelper extends TestContext {
    private static final String COLLECTION_ENDPOINT = ConfigurationReader.get("collection");

    public static void getCollection(String apiKey, int page, int pageSize) {
        response = given()
                .queryParam("key", apiKey)
                .queryParam("p", page)
                .queryParam("ps", pageSize)
                .when()
                .get(COLLECTION_ENDPOINT);
    }

}
