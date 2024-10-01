Feature: Validate the API for Patient Negative scenarios

Scenario: Create Patients Negative Test Cases 
Given User gets test data from the sheet for new patient  with scenario name is not "validPatient"
When  User sends the HTTP POST request to create new patient
Then Status code is varified