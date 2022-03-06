import java.util.Properties;

import org.apache.http.auth.AUTH;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.API.utils.FileUtils;
import com.API.utils.PropertyUtils;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class AddUser {
	FileUtils file = new FileUtils();
	Properties prop;

		
		@Test
		public void createUser() {
			prop = PropertyUtils.getProperty();
			RestAssured.baseURI = "https://supervillain.herokuapp.com/";
			RequestSpecification request = RestAssured.given();

			String tokengenerated = (String) prop.getProperty("jwt");
			request.log().all();
			request.header("Authorization", tokengenerated);
			request.header("Content-Type", "application/json");
			Response response = request.body(file.readJson("UserList.json")).post("https://supervillain.herokuapp.com/v1/user").then().extract().response();
			response.then().log().all();
			int statusCode = response.getStatusCode();
			Assert.assertEquals(201, statusCode);
			
		}
		
		
	
	
}

