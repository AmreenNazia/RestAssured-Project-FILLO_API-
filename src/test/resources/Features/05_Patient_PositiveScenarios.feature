Feature: Validate the API for Patient positive scenarios

Scenario: Create Patients and login as patient 
Given User gets test data from the sheet for new patient  with scenario name "validPatient"
When User sends the HTTP POST request with the request body to create new patient
Then Patient is created with an password
Then Patient logs in and patient token is generated