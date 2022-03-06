import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.API.utils.FileUtils;
import com.API.utils.PropertyUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class VerifyToken {

	FileUtils file = new FileUtils();
	Properties prop;

	@Test
	public void verify_AccessToken() {
		prop = PropertyUtils.getProperty();
		RestAssured.baseURI = "https://supervillain.herokuapp.com/";
		RequestSpecification request = RestAssured.given();

		String tokengenerated = (String) prop.getProperty("jwt");
		request.log().all();
		request.header("Authorization", tokengenerated);
		request.header("Content-Type", "application/json");
		Response response = request.get("https://supervillain.herokuapp.com/auth/verifytoken").then().extract().response();
		response.then().log().all();
		int statusCode = response.getStatusCode();
		Assert.assertEquals(200, statusCode);
	}

}

