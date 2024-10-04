Feature: Validate the API for Patient Negative scenarios

  Background: 
    Given Collect the test data using FilloAPI with scenario name "valid_Credentials"
    When User send the HTTP POST request with the request body

  Scenario: Creating Dietician
    Given Collects the test data using FilloAPI with scenario name "valid_data02"
    When User sends the HTTP POST request with the request body
    Then User validates with expected code and response body

  Scenario: Create Patients and login as patient
    Given User gets test data from the sheet for new patient  with test case name "Negative"
    When User sends the HTTP POST request with the request body to create new patient
    Then Patient logs in and patient token is generated
