package stepdefinition;

import java.util.List;
import java.util.Map;

import io.cucumber.java.en.*;
import library.ApiConstant;
import utilities.ExcelReader;
import utilities.LoggerLoad;
import utilities.RestUtils;

public class LoginSteps extends RestUtils{
	
	
	List<Map<String, String>> testdata;
	ApiConstant apiconstant = new ApiConstant();
	
	
	//Postive TestCase
	@Given("Collect the test data using FilloAPI with scenario name {string}")
	public void collect_the_test_data_using_fillo_api_with_scenario_name(String Scenario) 
	{
		String login_Query = "Select * From LoginSheet Where Scenario = '"+Scenario+"'";
		System.out.println(login_Query);
		 
		testdata = ExcelReader.getExcelDataWithFilloAPI(apiconstant.PATH,login_Query);
		System.out.println(testdata.toString());
//		 LoggerLoad.info("=======Fillo API is up and Running======");

	}

	@When("User send the HTTP POST request with the request body")
	public void user_send_the_http_post_request_with_the_request_body() {
		
		
	     
	}

	@Then("User validates with expected status code")
	public void user_validates_with_expected_status_code() {
	    
	}




}
