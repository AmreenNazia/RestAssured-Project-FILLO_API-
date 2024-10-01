package pojo;

import lombok.Data;

@Data
public class LoginPojo {
	
	private String username;
	private String password;
	private String Scenario;
	private String endpoint;
	private String Method;
	private String expectedStatusCode;
	private String tokenName;
	
	
}
