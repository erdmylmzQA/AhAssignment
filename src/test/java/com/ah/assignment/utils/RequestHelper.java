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

    public static void getCollectionWithParameters(String apiKey, int page, int pageSize, String parameters) {
        response = given()
                .queryParam("key", apiKey)
                .queryParam("p", page)
                .queryParam("ps", pageSize)
                .queryParam("s", parameters)
                .when()
                .get(COLLECTION_ENDPOINT);

    }

    public static void getCollectionWithKeyword(String apiKey, String keyword, int page, int pageSize) {
        response = given()
                .queryParam("key", apiKey)
                .queryParam("q", keyword.replaceAll("[\\p{Punct}]", " "))
                .queryParam("p", page)
                .queryParam("ps", pageSize)
                .when()
                .get(COLLECTION_ENDPOINT);

    }

    public static void getCollectionInvolvedMaker(String apiKey, String involvedMaker, int page, int pageSize) {
        response = given()
                .queryParam("key", apiKey)
                .queryParam("involvedMaker", TestUtils.extractFullName(involvedMaker))
                .queryParam("p", page)
                .queryParam("ps", pageSize)
                .when()
                .get(COLLECTION_ENDPOINT);

    }

}
