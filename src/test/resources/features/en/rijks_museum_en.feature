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

    #Collection API
  Scenario Outline: Sort collections
    When user sends a get request with sorting "<parameters>"
    Then the response should be sorted
    And the status code should be 200
    Examples: sort with parameter
      | parameters   |
      | relevance    |
      | objecttype   |
      | chronologic  |
      | achronologic |
      | artist       |
      | artistdesc   |

    #Collection API
  Scenario: Search for an existing item
    When user makes a search with a keyword
    Then the response should contain search results related to the keyword
    And the status code should be 200

    #Collection API
    #Please see the bug report
  Scenario: Filter collections by principalOrFirstMakers
    When user makes a request to the collection endpoint with filter by the involvedMaker
    Then the response should contain collections regarding the involvedMaker
    And the status code should be 200

    #Collection Details API
  Scenario: Retrieving details of a specific object
    When user makes a request with an object number for the object details
    Then the response should contain the object's details and should match the Json schema
    And the status code should be 200

     #Collection Details API
  Scenario: Retrieving details of a specific object by filtering
    When user sends a get request with filtering parameters in English
    Then user makes a request with an object number for the object details
    Then the response should contain the filtering parameters in the object details
    And the status code should be 200

    #Collection Details API
    #Please see the bug report
  Scenario: Sending a request with an invalid object number
    When user makes a request to the object details endpoint with "!$(!)}"
    Then the response should not contain any object
    And the error message is not "Invalid Object Number"