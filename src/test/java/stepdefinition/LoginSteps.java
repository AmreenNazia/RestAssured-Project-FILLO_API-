package stepdefinition;

import static io.restassured.RestAssured.given;


import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

import org.testng.Assert;

import com.google.gson.Gson;

import io.cucumber.java.en.*;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import library.ApiConstant;
import library.Tokens;
import pojo.LoginPojo;
import utilities.ExcelReader;
import utilities.LoggerLoad;
import utilities.RestUtils;

public class LoginSteps extends RestUtils{
	
	
	List<Map<String, String>> testdata;
	ApiConstant apiconstant = new ApiConstant();
	LoginPojo loginpojo = new LoginPojo();
	String reqbody;
	RequestSpecification request;
	 
	
	
	//Postive TestCase
	@Given("Collect the test data using FilloAPI with scenario name {string}")
	public void collect_the_test_data_using_fillo_api_with_scenario_name(String Scenario) 
	{
		String login_Query = "Select * From LoginSheet Where Scenario = '"+Scenario+"'";
		testdata = ExcelReader.getExcelDataWithFilloAPI(apiconstant.PATH,login_Query);
		 for(Map<String,String> data : testdata)
		 {
			 loginpojo.setScenario(data.get("Scenario"));
			 loginpojo.setUsername(data.get("username"));
			 loginpojo.setPassword(data.get("password"));
			 loginpojo.setEndpoint(data.get("endpoint"));
			 loginpojo.setMethod(data.get("method"));
			 loginpojo.setExpectedStatusCode(data.get("expectedStatusCode"));
			 loginpojo.setTokenName(data.get("tokenName"));
			 reqbody = new Gson().toJson(loginpojo);
			 
		 }
		 
		 LoggerLoad.info("****Collected the Testdata and Stored in Pojos****");

	}

	@When("User send the HTTP POST request with the request body")
	public void user_send_the_http_post_request_with_the_request_body() throws FileNotFoundException {
		
		request = given().spec(requestSpecification()).body(reqbody);
		response = request.when().post(loginpojo.getEndpoint());
		LoggerLoad.info("*** Responsebody ***");
		response.then().log().all().extract();
		String token = response.path("token");
		Tokens.TokenMap.put(loginpojo.getTokenName(),token);
		
		 
		
	     
	}

	@Then("User validates with expected status code")
	public void user_validates_with_expected_status_code() {
	    
		 Assert.assertEquals(response.getStatusCode(), Integer.parseInt(loginpojo.getExpectedStatusCode()));
	}

// Negative Scenario with invalid Method name
	
	@When("User send the HTTP GET request with the request body")
	public void user_send_the_http_get_request_with_the_request_body() throws FileNotFoundException {
		
		request = given().spec(requestSpecification()).body(reqbody);
		response = request.when().get(loginpojo.getEndpoint());
		LoggerLoad.info("*** Responsebody ***");
		response.then().log().all().extract();
		
	     
	}

}
