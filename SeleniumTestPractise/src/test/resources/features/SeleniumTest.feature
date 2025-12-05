
Feature: To test normal selenium functionality
  Scenario: Basic functionalities to test in selenium
    Given User is on the Google Home Page
    When User types "Selenium WebDriver" in the search box
    And User clicks on the Google Search button
    Then User should see results related to "Selenium WebDriver"