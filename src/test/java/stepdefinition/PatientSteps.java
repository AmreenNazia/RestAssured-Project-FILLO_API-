package stepdefinition;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

import org.testng.Assert;

import com.google.gson.Gson;

import io.cucumber.java.en.*;
import io.restassured.specification.RequestSpecification;
import library.ApiConstant;
import library.Tokens;
import pojo.DieticianPojo;
import pojo.LoginPojo;
import pojo.PatientPojo;
import utilities.ExcelReader;
import utilities.RestUtils;

public class PatientSteps extends RestUtils {
	ApiConstant apiconstant = new ApiConstant();
	RequestSpecification request;
	PatientPojo patientPojo = new PatientPojo();
	List<Map<String, String>> listOfRows;
	String reqbody;
	String username;
	String password;

	@Given("User gets test data from the sheet for new patient  with test case name {string}")
	public void user_gets_test_data_from_the_sheet_for_new_patient_with_test_case_name(String testCase) {

		String getPatientQuery = "Select * From Patient Where TestCase = '" + testCase + "'";
		System.out.println("getPatientQuery =: " + getPatientQuery);
		listOfRows = ExcelReader.getExcelDataWithFilloAPI(apiconstant.PATIENT_DATA_PATH, getPatientQuery);
		System.out.println("no of rows = " + listOfRows);

	}

	// Positive Scenarios
	@When("User sends the HTTP POST request with the request body to create new patient")
	public void user_sends_the_http_post_request_with_the_request_body_to_create_new_patient()
			throws FileNotFoundException {
		for (Map<String, String> data : listOfRows) {
			System.out.println("data = :" + data);

			// Setting values to Patient Pojo
			createRequestBody(data);

			reqbody = new Gson().toJson(patientPojo);
			System.out.println("reqbody : ==" + reqbody);
			System.out.println("dietician id  :=" + Tokens.dietIdMap.get("dieticianIdForPatient"));

			System.out.println("dietId map: " + Tokens.dietIdMap.size());
			request = given().spec(requestSpecification()).body(reqbody);
			System.out.println("request : " + request);
			response = request.header("Authorization", "Bearer " + Tokens.TokenMap.get("AdminToken")).when()
					.post(apiconstant.CREATE_PATIENT_ENDPOINT);
			response.then().log().all().extract();
			System.out.println("admin token:>>>>>" + Tokens.TokenMap.get("AdminToken"));
			System.out.println("response ========= " + response);
			if (patientPojo.getScenario().equalsIgnoreCase("validPatientForCURD")) {
				String idForCURD = response.path("id");
				Tokens.patIdMap.put("validPatientForCURD", idForCURD);
				System.out.println("idForCURD = :" + Tokens.patIdMap.get("validPatientForCURD"));

			} else if (patientPojo.getScenario().equalsIgnoreCase("validPatientForReports")) {
				String idForReports = response.path("id");
				Tokens.patIdMap.put("validPatientForReports", idForReports);

				username = response.path("email");
				password = response.path("password");

				Tokens.patientCredentials.put("username", username);
				Tokens.patientCredentials.put("password", password);
			}

		}
	}

	@Then("Patient logs in and patient token is generated")
	public void patient_logs_in_and_patient_token_is_generated() {
		Assert.assertEquals(response.getStatusCode(), Integer.parseInt(patientPojo.getExpectedStatusCode()));
	}

// Negative Scenarios
	@When("User sends the HTTP POST request to create new patient")
	public void user_sends_the_http_post_request_to_create_new_patient() throws FileNotFoundException {
		for (Map<String, String> data : listOfRows) {

			// setting values to Patient Pojo
			createRequestBody(data);

			reqbody = new Gson().toJson(patientPojo);
			if (patientPojo.getScenario().equalsIgnoreCase("no_auth")) {
				request = given().spec(requestSpecification()).body(reqbody);
				response = request.when().post(apiconstant.CREATE_PATIENT_ENDPOINT);
				response.then().log().all().extract();
			} else {
				request = given().spec(requestSpecification()).body(reqbody);
				response = request.header("Authorization", "Bearer " + Tokens.TokenMap.get("AdminToken")).when()
						.post(apiconstant.CREATE_PATIENT_ENDPOINT);
				response.then().log().all().extract();
			}
		}
	}

	@Then("Status code is varified")
	public void status_code_is_varified() {
		Assert.assertEquals(response.getStatusCode(), Integer.parseInt(patientPojo.getExpectedStatusCode()));
	}

	// Setting values to Patient Pojo
	private void createRequestBody(Map<String, String> data) {
		patientPojo.setScenario(data.get("Scenario"));
		// System.out.println("Scenario = :"+ data.get("Scenario"));
		patientPojo.setToken(data.get("Token"));
		patientPojo.setMethod(data.get("Method"));
		patientPojo.setFirstName(data.get("firstName"));

		patientPojo.setLastName(data.get("lastName"));
		patientPojo.setContactNumber(data.get("contactNumber"));
		patientPojo.setEmail(data.get("email"));
		String[] allergies = data.get("allergies").split(",");
		patientPojo.setAllergies(allergies);

		patientPojo.setFoodPreference(data.get("foodPreference"));
		patientPojo.setCuisineCategory(data.get("cuisineCategory"));
		patientPojo.setExpectedStatusCode(data.get("expectedStatusCode"));

	}

}
