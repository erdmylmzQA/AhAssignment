package com.ah.assignment.utils;



import com.ah.assignment.testBase.TestContext;

import static io.restassured.RestAssured.given;

public class RequestHelper extends TestContext {
    private static final String COLLECTION_ENDPOINT = ConfigurationReader.get("collection");
    private static final String USERSETS_ENDPOINT = ConfigurationReader.get("userSets");

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

    public static void getObjectDetails(String apiKey, String objectNumber) {
        response = given()
                .queryParam("key", apiKey)
                .when()
                .get(COLLECTION_ENDPOINT + "/{objectNumber}", objectNumber);
    }

    public static void getFilteredCollection(String apiKey, String place, String type, String technique, int period, boolean imgOnly, String material) {
        response = given()
                .queryParam("key", apiKey)
                .queryParam("place", place)
                .queryParam("type", type)
                .queryParam("technique", technique)
                .queryParam("f.dating.period", period)
                .queryParam("imgonly", imgOnly)
                .queryParam("material", material)
                .queryParam("p", 1)
                .queryParam("ps", 50)
                .when()
                .get(COLLECTION_ENDPOINT);
    }

    public static void getCollectionImage(String apiKey, String objectNumber) {
        response = given()
                .queryParam("key", apiKey)
                .when()
                .get(COLLECTION_ENDPOINT + "/{objectNumber}/tiles", objectNumber);
    }

    public static void getUserSets(String apiKey, int page) {
        response = given()
                .queryParam("key", apiKey)
                .queryParam("format", "json")
                .queryParam("p", page)
                .when()
                .get(USERSETS_ENDPOINT);
    }

}
