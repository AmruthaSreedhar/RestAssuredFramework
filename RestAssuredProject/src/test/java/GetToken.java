import org.apache.http.auth.AUTH;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.API.utils.FileUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetToken {
	FileUtils file = new FileUtils();

	@Test
	public void get_AccessToken() {
		RestAssured.baseURI = "https://supervillain.herokuapp.com/auth/verifytoken";

		RequestSpecification request = RestAssured.given();
		String payload = file.readJson("getToken.json");
		request.header("Content-Type", "application/json");
		request.header("Authrizatio","");
		Response responseFromGenerateToken = request.body(payload).log().all()
				.post("https://supervillain.herokuapp.com/auth/gentoken");
		responseFromGenerateToken.then().log().all();
		
		String jsonString = responseFromGenerateToken.getBody().asString();
		String tokengenerated = JsonPath.from(jsonString).get("token");
		request.header("Authorization", "Bearer" + tokengenerated).header("Content-Type", "application/json");
	
			RequestSpecification Httprequest = RestAssured.given();
			Response response =  Httprequest.request(Method.GET,"https://supervillain.herokuapp.com/auth/verifytoken");
			int statusCode = response.getStatusCode();
			Assert.assertEquals(200, statusCode);


	
}
}