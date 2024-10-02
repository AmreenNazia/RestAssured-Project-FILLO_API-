Feature: Validate the API for Login negative scenarios

Scenario: Invalid endppoint for Creating Admin Token  
Given Collect the test data using FilloAPI with scenario name "invalid_endpoint"
When User send the HTTP POST request with the request body
Then User validates with expected status code

Scenario: Invalid method for Creating Admin Token  
Given Collect the test data using FilloAPI with scenario name "invalid_method"
When User send the HTTP GET request with the request body
Then User validates with expected status code

Scenario: Invalid method for Creating Admin Token  
Given Collect the test data using FilloAPI with scenario name "invalid_username"
When User send the HTTP POST request with the request body
Then User validates with expected status code

Scenario: Invalid method for Creating Admin Token  
Given Collect the test data using FilloAPI with scenario name "invalid_password"
When User send the HTTP POST request with the request body
Then User validates with expected status code

Scenario: Invalid method for Creating Admin Token  
Given Collect the test data using FilloAPI with scenario name "empty_username"
When User send the HTTP POST request with the request body
Then User validates with expected status code

Scenario: Invalid method for Creating Admin Token  
Given Collect the test data using FilloAPI with scenario name "alphanumeric_username"
When User send the HTTP POST request with the request body
Then User validates with expected status code

Scenario: Invalid method for Creating Admin Token  
Given Collect the test data using FilloAPI with scenario name "specialchar_username"
When User send the HTTP POST request with the request body
Then User validates with expected status code

Scenario: Invalid method for Creating Admin Token  
Given Collect the test data using FilloAPI with scenario name "empty_password"
When User send the HTTP POST request with the request body
Then User validates with expected status code

Scenario: Invalid method for Creating Admin Token  
Given Collect the test data using FilloAPI with scenario name "specialchar_password"
When User send the HTTP POST request with the request body
Then User validates with expected status code

Scenario: Invalid method for Creating Admin Token  
Given Collect the test data using FilloAPI with scenario name "invalid_password"
When User send the HTTP POST request with the request body
Then User validates with expected status code

Scenario: Invalid method for Creating Admin Token  
Given Collect the test data using FilloAPI with scenario name "invalid_password"
When User send the HTTP POST request with the request body
Then User validates with expected status code