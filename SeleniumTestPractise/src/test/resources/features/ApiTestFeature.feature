Feature: Validate the API testing Functionality
  Scenario Outline: Basic functionality testing
    Given User sends a GET request to "<endpoint>"
    And user sends put request that exists
    And user sends put request that does not exists
    And user sends delete request that exists
    And user sends delete request that does not exists
    And user do patch request that exists
    And user do patch request that does not exists
    And check on the rate limit from the response
    Examples:
      |  endpoint |
    |https://jsonplaceholder.typicode.com |
