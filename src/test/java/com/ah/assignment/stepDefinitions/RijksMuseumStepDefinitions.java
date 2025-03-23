package com.ah.assignment.stepDefinitions;

import com.ah.assignment.testBase.TestContext;
import com.ah.assignment.utils.RequestHelper;
import com.ah.assignment.utils.TestUtils;
import io.cucumber.java.en.*;


import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

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

    @When("user makes a request with an invalid page number {int}")
    public void userMakesARequestWithAnInvalidPageNumber(int invalidPageNumber) {
        pageSize = pageSize == 0 ? 10 : pageSize;
        RequestHelper.getCollection(api_key, invalidPageNumber, pageSize);
    }

    @When("user makes a request for collections by {int} and {int}")
    public void userMakesARequestForCollectionsByPNumberAndPsNumber(int pNumber, int psNumber) {
        pageSize = 100;
        RequestHelper.getCollection(api_key, pNumber, psNumber);
    }

    @Then("the response should return an empty result")
    public void theResponseShouldReturnAnEmptyResult() {
        response.then().statusCode(200);
        assertTrue(TestUtils.extractList(response,"artObjects").isEmpty());
    }

    @When("user makes a request for page {int} of collections")
    public void userMakesARequestForPageOfCollections(int pageNumber) {
        pageSize = pageSize == 0 ? 10 : pageSize;
        if (pageNumber == 1) {
            RequestHelper.getCollection(api_key, pageNumber, pageSize);
        } else {
            secondPageResponse = given()
                    .queryParam("key", api_key)
                    .queryParam("p", pageNumber)
                    .queryParam("ps", pageSize)
                    .when()
                    .get("/collection");
        }
    }

    @Then("the results of the pages should be different")
    public void theResultsOfPageAndPageShouldBeDifferent() {
        assertNotEquals(TestUtils.extractList(response,"artObjects"), TestUtils.extractList(secondPageResponse,"artObjects"));
    }
}
