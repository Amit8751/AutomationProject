Feature: Verification of JSON file data and Excel file data with database
  This feature is responsible for verification of JSON file data and Excel file data with database

  Scenario: Verify JSON file data is matching with database
    When I read JSON file
    And I read data from database
    And I compare JSON file data with database data
    Then JSON data and database data should be matched successfully

  Scenario: Verify Excel file data is matching with database
    When I read data from excel
    And I read data from database
    And I compare Excel file data with database data
    Then Excel file data and database data should be matched successfully
