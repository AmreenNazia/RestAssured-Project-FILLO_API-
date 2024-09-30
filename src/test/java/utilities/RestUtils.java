package utilities;

import java.io.FileNotFoundException;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import library.ApiConstant;


public class RestUtils extends ApiConstant {
	
	public static RequestSpecification req;
	public static Response response;
	public static ResponseSpecification resSpec;
	

//	public static ResourceBundle routes = ResourceBundle.getBundle("Routes");
	
	

	
	public RequestSpecification requestSpecification() throws FileNotFoundException {
		
		if(req==null)
		{
		PrintStream log=new PrintStream (new FileOutputStream("logging.txt"));
		 req=new RequestSpecBuilder().setBaseUri(ApiConstant.BaseUri)
				 .addFilter(RequestLoggingFilter.logRequestTo(log))
				 .addFilter(ResponseLoggingFilter.logResponseTo(log))
				.setContentType(ContentType.JSON)
				.setAccept(ContentType.JSON).build();
		 return req;
		}
		return req;
	}
	
	public ResponseSpecification resSpecBuilder() {
		 resSpec = new ResponseSpecBuilder()
				.expectContentType(ContentType.JSON)
				.build();
		return resSpec;
	}
	// resspec=new ResponseSpecBuilder().expectStatusCode(200).expectContentType("application/json;charset=UTF-8").build();
	
	public String UserKeyJson(Response response, String key) {
		String getResponse = response.asString();
		JsonPath js = new JsonPath(getResponse);
		String value = js.getString(key);
		
		return value;
	}
	
}
