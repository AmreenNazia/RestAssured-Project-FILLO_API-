package library;

public class ApiConstant {
	
	public static String BaseUri = "http://localhost:8081";
	public String PATH = "./src/test/resources/DataFile/DataFile.xlsx";
	public  String PATIENT_DATA_PATH = "./src/test/resources/DataFile/Rest_Assured_Practice_Test_Data.xlsx";
	public  String CREATE_PATIENT_ENDPOINT = "/v1/dieticians/"+ Tokens.dietIdMap.get("dieticianIdForPatient") +"/patients";
	//public String CREATE_PATIENT_ENDPOINT = "/v1/dieticians/"+dietId+"/patients";

}
