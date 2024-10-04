package stepdefinition;

import java.io.FileNotFoundException;

import java.util.List;
import java.util.Map;

import org.testng.Assert;

import static io.restassured.RestAssured.given;
import com.google.gson.Gson;

import io.cucumber.java.en.*;
import io.restassured.specification.RequestSpecification;
import library.ApiConstant;
import library.Tokens;
import pojo.DieticianPojo;
import pojo.LoginPojo;
import utilities.ExcelReader;
import utilities.RestUtils;

public class DieticianSteps extends RestUtils {
	ApiConstant apiconstant = new ApiConstant();
	RequestSpecification request;
	DieticianPojo dieticianpojo = new DieticianPojo();
	List<Map<String, String>> testdata;
	String reqbody;
	LoginPojo loginpojo = new LoginPojo();
	String username;
	String password;

	@Given("Collects the test data using FilloAPI with scenario name {string}")
	public void collects_the_test_data_using_fillo_api_with_scenario_name(String Scenario) {

		String dieticianQuery = "Select * From dietician Where Scenario = '" + Scenario + "'";
		testdata = ExcelReader.getExcelDataWithFilloAPI(apiconstant.PATH, dieticianQuery);
		for (Map<String, String> data : testdata) {
			dieticianpojo.setFirstName(data.get("firstName"));
			dieticianpojo.setLastName(data.get("lastName"));
			dieticianpojo.setContactNumber(data.get("contactNumber"));
			dieticianpojo.setDateOfBirth(data.get("dateOfBirth"));
			dieticianpojo.setEducation(data.get("education"));
			dieticianpojo.setEmail(data.get("email"));
			dieticianpojo.setHospitalCity(data.get("hospitalCity"));
			dieticianpojo.setHospitalName(data.get("hospitalName"));
			dieticianpojo.setHospitalStreet(data.get("hospitalStreet"));
			dieticianpojo.setHospitalZipCode(data.get("hospitalZipcode"));
			dieticianpojo.setDieticianId(data.get("dieticianID"));
			dieticianpojo.setScenario(data.get("Scenario"));
			dieticianpojo.setEndpoint(data.get("endpoint"));
			dieticianpojo.setExpectedStatuscode(data.get("expectedCode"));
			dieticianpojo.setDieticianIdForPatient(data.get("dieticianIdForPatient"));
			dieticianpojo.setUsername(data.get("username"));
			dieticianpojo.setPassword(data.get("password"));
			reqbody = new Gson().toJson(dieticianpojo);

		}

	}

	@When("User sends the HTTP POST request with the request body")
	public void user_sends_the_http_post_request_with_the_request_body() throws FileNotFoundException {

		if (dieticianpojo.getScenario().equalsIgnoreCase("no_auth")) {
			request = given().spec(requestSpecification()).body(reqbody);
			response = request.when().post(dieticianpojo.getEndpoint());
			response.then().log().all().extract();
		} else {
			request = given().spec(requestSpecification()).body(reqbody);
			response = request.header("Authorization", "Bearer " + Tokens.TokenMap.get("AdminToken")).when()
					.post(dieticianpojo.getEndpoint());
			response.then().log().all().extract();
		}

		if (dieticianpojo.getScenario().equalsIgnoreCase("valid_data01")) {
			String dieticianId = response.path("id");
			Tokens.dietIdMap.put("dieticianId", dieticianId);

		} else if (dieticianpojo.getScenario().equalsIgnoreCase("valid_data02")) {
			String dieticianId01 = response.path("id");
			Tokens.dietIdMap.put("dieticianIdForPatient", dieticianId01);

			username = response.path("email");
			password = response.path("password");

			Tokens.dietcredentials.put("username", username);
			Tokens.dietcredentials.put("password", password);

		}
	}

	@Then("User validates with expected code and response body")
	public void user_validates_with_expected_code_and_response_body() {

		Assert.assertEquals(response.getStatusCode(), Integer.parseInt(dieticianpojo.getExpectedStatuscode()));

	}

	// Dietician Token
	@Given("Collects test data using FilloAPI with scenario name {string}")
	public void collects_test_data_using_fillo_api_with_scenario_name(String Scenario) {

		String dieticianQuery = "Select * From dietician Where Scenario = '" + Scenario + "'";
		testdata = ExcelReader.getExcelDataWithFilloAPI(apiconstant.PATH, dieticianQuery);

		for (Map<String, String> data : testdata) {
			dieticianpojo.setUsername(Tokens.dietcredentials.get("username"));
			dieticianpojo.setPassword(Tokens.dietcredentials.get("password"));
			dieticianpojo.setExpectedStatuscode(data.get("expectedCode"));
			dieticianpojo.setEndpoint2(data.get("endpoint"));
			reqbody = new Gson().toJson(dieticianpojo);
			System.out.println(reqbody.toString());

		}
	}

	@When("User sends HTTP POST request with the request body")
	public void user_sends_http_post_request_with_the_request_body() throws FileNotFoundException {
		request = given().spec(requestSpecification()).body(reqbody);
		response = request.header("Authorization", "Bearer " + Tokens.TokenMap.get("AdminToken")).when()
				.post(dieticianpojo.getEndpoint2());
		response.then().log().all().extract();

		String dieticiantoken = response.path("token");

		Tokens.TokenMap.put("dieticianToken", dieticiantoken);
		System.out.println(Tokens.TokenMap.get("dieticianToken"));
	}

	@Then("User validate with expected code and response body")
	public void user_validate_with_expected_code_and_response_body() {
		Assert.assertEquals(response.getStatusCode(), Integer.parseInt(dieticianpojo.getExpectedStatuscode()));
	}

}
