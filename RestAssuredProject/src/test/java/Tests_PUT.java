

	import java.util.HashMap;
	import java.util.Map;
import java.util.Properties;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.API.utils.FileUtils;
import com.API.utils.PropertyUtils;
import com.google.gson.JsonObject;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;


	public class Tests_PUT {
		  public static Map<String, String> map = new HashMap<String, String>();
		  FileUtils file = new FileUtils();
			Properties prop;
		
		  @BeforeTest
		    public void putdata(){
			  
			 
		        map.put("username", "newest");
		        map.put("score", "1000");
		         
		        RestAssured.baseURI = "https://supervillain.herokuapp.com/";
		        RestAssured.basePath = "/v1/user";
		  }
		
	@Test
		public void test_for_put () {

    prop = PropertyUtils.getProperty();
	RestAssured.baseURI = "https://supervillain.herokuapp.com/";
	RequestSpecification request = RestAssured.given();

	String tokengenerated = (String) prop.getProperty("jwt");
	request.log().all();
	request.header("Authorization", tokengenerated);
	request.header("Content-Type", "application/json");
	Response response = request.body(file.readJson("UpdateUser.json")).put("https://supervillain.herokuapp.com/v1/user").then().extract().response();
	response.then().log().all();
	int statusCode = response.getStatusCode();
	Assert.assertEquals(201, statusCode);
	
}
	
				}
		
	



