Feature: Verification of JSON file data with database
  This feature is responsible for verification of JSON file data with database

  Scenario: Verify JSON file data is matching with database
    When I read JSON file
    And I read data from database
    And I compare JSON file data with database data
    Then JSON data and database data should be matched successfully


