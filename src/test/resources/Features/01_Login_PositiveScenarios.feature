Feature: Validate the API for Login positive scenarios

Scenario: Creating Admin Token  
Given Collect the test data using FilloAPI with scenario name "valid_Credentials"
When User send the HTTP POST request with the request body
Then User validates with expected status code