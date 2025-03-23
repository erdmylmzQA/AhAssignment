package com.ah.assignment.testBase;

import com.ah.assignment.utils.ConfigurationReader;
import com.ah.assignment.utils.DotEnvToSystemEnv;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TestContext {
    protected static Response response;
    protected static int pageSize;
    protected static Response secondPageResponse;

    protected static void baseUrlEn() {
        RestAssured.baseURI = ConfigurationReader.get("baseUrlEn");
    }

    protected String api_key = DotEnvToSystemEnv.getEnvValue("API_KEY");
}
