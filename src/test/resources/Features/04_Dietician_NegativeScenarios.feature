Feature: Validate the API for Dietician negative scenarios

Background:
Given Collect the test data using FilloAPI with scenario name "valid_Credentials"
When User send the HTTP POST request with the request body

Scenario: Invalid Scenarios Creating Dietician 
Given Collects the test data using FilloAPI with scenario name "invalid_firstname"
When User sends the HTTP POST request with the request body
Then User validates with expected code and response body

Scenario: Invalid Scenarios Creating Dietician 
Given Collects the test data using FilloAPI with scenario name "invalid_lastname"
When User sends the HTTP POST request with the request body
Then User validates with expected code and response body

Scenario: Invalid Scenarios Creating Dietician 
Given Collects the test data using FilloAPI with scenario name "invalid_contactNumber"
When User sends the HTTP POST request with the request body
Then User validates with expected code and response body

Scenario: Invalid Scenarios Creating Dietician 
Given Collects the test data using FilloAPI with scenario name "invalid_dateofBirth"
When User sends the HTTP POST request with the request body
Then User validates with expected code and response body

Scenario: Invalid Scenarios Creating Dietician 
Given Collects the test data using FilloAPI with scenario name "invalid_email"
When User sends the HTTP POST request with the request body
Then User validates with expected code and response body

Scenario: Invalid Scenarios Creating Dietician 
Given Collects the test data using FilloAPI with scenario name "invalid_education"
When User sends the HTTP POST request with the request body
Then User validates with expected code and response body

Scenario: Invalid Scenarios Creating Dietician 
Given Collects the test data using FilloAPI with scenario name "invalid_endpoint"
When User sends the HTTP POST request with the request body
Then User validates with expected code and response body

@test
Scenario: Invalid Scenarios Creating Dietician 
Given Collects the test data using FilloAPI with scenario name "no_auth"
When User sends the HTTP POST request with the request body
Then User validates with expected code and response body


