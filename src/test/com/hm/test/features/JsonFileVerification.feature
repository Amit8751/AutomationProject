Feature: Verification of JSON file data with database
  This feature is responsible for verification of JSON file data with database

  Scenario: Verify JSON file data is matching with database
    When I read JSON file
    And I read data from database
    And I compare JSON file data with database data
    Then JSON data and database data should be matched successfully


 # Scenario: Verify JSON file data is inserted into database
  #  When I read JSON file
   # And I insert JSON file data in database
  #  And I read data from database
  #  And I compare JSON file data with database data
  #  Then JSON data inserted into database successfully

 # Scenario: Verify JSON file data is inserted into database
  # When I read JSON file
  #  And I insert JSON file data in excel file
  # And I read data from excel
  #  And I compare JSON file data with excel data
  #  Then JSON data and excel data should be matched successfully
