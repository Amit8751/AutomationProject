Feature: Customer Creation Feature
  This feature is responsible for customer creation in XYZ Bank

#  Scenario: Check Adding Customer by providing all the mandatory details
#    And I Ensure application is opened successfully
#    When I click on Bank Manager Login Button
#    Then I click on Add Customer Button
#    When I enter First_Name Last_Name And Post Code
#      | First_Name | Last_Name | Post_Code |
#      | Auto        |  User      |  400023   |
#    And I click on Add Customer Button Below
#    Then I Should see the customer should be added Successfully


  Scenario Outline: Search the Customer details
      And I Ensure application is opened successfully
      When I click on Bank Manager Login Button
      And  I click on Customers Button
      Then I should see the search customer text box
      When I enter first_name in search field as "<first_name>"
      Then Customer details should be visible Successfully
      Examples:
          | first_name |
          | ABC       |
          |  Harry    |




