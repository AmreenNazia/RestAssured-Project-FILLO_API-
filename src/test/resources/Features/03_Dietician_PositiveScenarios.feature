Feature: Validate the API for Dietician positive scenarios

Background:
Given Collect the test data using FilloAPI with scenario name "valid_Credentials"
When User send the HTTP POST request with the request body

Scenario: Creating Dietician 
Given Collects the test data using FilloAPI with scenario name "valid_data01"
When User sends the HTTP POST request with the request body
Then User validates with expected code and response body

Scenario: Creating Dietician 
Given Collects the test data using FilloAPI with scenario name "valid_data02"
When User sends the HTTP POST request with the request body
Then User validates with expected code and response body

Scenario: Creating Dietician token
Given Collects test data using FilloAPI with scenario name "dieticianToken"
When User sends HTTP POST request with the request body
Then User validate with expected code and response body

