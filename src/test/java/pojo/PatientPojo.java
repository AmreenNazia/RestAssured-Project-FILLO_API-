package pojo;

import lombok.Data;

@Data
public class PatientPojo {
	private String Scenario; 
	private String token;
	private String method;
	private String firstName;
	private String lastName;
	private String contactNumber;
	private String email;
	private String[] allergies;
	private String foodPreference;
	private String cuisineCategory;
	private String expectedStatusCode;
	private String password;
	

}
