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

    #Collection API
  Scenario: Request with invalid page number
    When user makes a request with an invalid page number -1
    Then the response should contain a list of collections with the page size
    And the status code should be 200

    #Collection API
  Scenario Outline: Request with very large page size number
    When user makes a request for collections by <pNumber> and <psNumber>
    Then the response should contain a list of collections with the page size
    And the status code should be 200
    Examples: page and ps numbers
      | pNumber | psNumber |
      | 100     | 100      |
      | 100     | 95       |
      | 100     | 105      |
      | 99      | 100      |
      | 99      | 105      |

    #Collection API
  Scenario Outline: Request with very large page number
    When user makes a request for collections by <pNumber> and <psNumber>
    Then the response should return an empty result
    And the status code should be 200
    Examples: page and ps numbers
      | pNumber | psNumber |
      | 101     | 100      |
      | 101     | 110      |
      | 101     | 95       |

    #Collection API
  Scenario: Retrieving multiple pages of collections
    When user makes a request for page 1 of collections
    And user makes a request for page 2 of collections
    Then the results of the pages should be different
    And the status code should be 200

