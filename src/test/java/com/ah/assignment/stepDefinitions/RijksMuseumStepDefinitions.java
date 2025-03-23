package com.ah.assignment.stepDefinitions;

import com.ah.assignment.testBase.TestContext;
import com.ah.assignment.utils.RequestHelper;
import com.ah.assignment.utils.TestUtils;
import io.cucumber.java.en.*;


import static org.junit.Assert.assertEquals;

public class RijksMuseumStepDefinitions extends TestContext {
    @Given("user wants to retrieve collection or object from the Rijksmuseum API in English")
    public void userWantsToRetrieveCollectionOrObjectFromTheRijksmuseumAPIInEn() {
        baseUrlEn();
    }

    @When("user sends a get request with an invalid API key")
    public void userSendsAGetRequestWithAnInvalidAPIKey() {
        RequestHelper.getCollection("invalidKey", 1, pageSize);
    }
    @Then("the status code should be {int}")
    public void validateStatusCode(int expectedStatusCode) {
        assertEquals(expectedStatusCode, response.statusCode());
    }
    @And("the error message should be {string}")
    public void theErrorMessageShouldBe(String errorMessage) {
        assertEquals(errorMessage, response.asString());
    }

    @When("user sends a get request with a page size")
    public void user_sends_a_get_request() {
        pageSize = TestUtils.getRandomPageSize();

        RequestHelper.getCollection(api_key, 1, pageSize);

    }

    @Then("the response should contain a list of collections with the page size")
    public void theResponseShouldContainAListOfCollectionsWithThePs() {
        response.then().statusCode(200);
        assertEquals(pageSize, response.jsonPath().getList("artObjects").size());
    }
}
