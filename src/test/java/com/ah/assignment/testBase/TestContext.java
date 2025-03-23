package com.ah.assignment.testBase;

import com.ah.assignment.utils.ConfigurationReader;
import com.ah.assignment.utils.DotEnvToSystemEnv;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TestContext {
    protected static Response response;
    protected static int pageSize;
    protected static Response secondPageResponse;
    protected static String searchKeyword;
    protected static String involvedMaker;
    protected static String objectNumber;
    protected static String userSetId;

    protected static void baseUrlEn() {
        RestAssured.baseURI = ConfigurationReader.get("baseUrlEn");
    }
    protected static void baseUrlNl() {
        RestAssured.baseURI = ConfigurationReader.get("baseUrlNl");
    }

    protected String api_key = DotEnvToSystemEnv.getEnvValue("API_KEY");
}
