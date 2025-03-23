@test
Feature: Rijks Museum API tests

  Background:
    Given user wants to retrieve collection or object from the Rijksmuseum API in English

    #Collections API
  Scenario: Sending a request with an Invalid API key
    When user sends a get request with an invalid API key
    Then the status code should be 401
    And the error message should be "Invalid key"

    #Collections  API
  Scenario: Retrieving the existing collections with page size
    When user sends a get request with a page size
    Then the response should contain a list of collections with the page size
    And the status code should be 200