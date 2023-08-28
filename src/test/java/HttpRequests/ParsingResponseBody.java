package HttpRequests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class ParsingResponseBody {

	
	@Test
	public void testParsingResponseBody() {
		given()
			.contentType("contentType.JSON")
		.when()
			.get("https://reqres.in/api/unknown/2")
		.then()
			.statusCode(200)
			.body("data.id", equalTo(2))
			.body("data.year", equalTo(2001))
			.log().all();
	}
	
	@Test
	public void testParsingResponseBodyApproach2() {
		Response response = given().contentType("contentType.JSON").when().get("https://reqres.in/api/unknown/2");
		String id = response.jsonPath().get("data.id").toString();
		String year = response.jsonPath().get("data.year").toString();
		Assert.assertEquals(id, "2");
		Assert.assertEquals(year, "2001");
	}
	
	@Test
	public void testParsingResponseBodyUsingJSONObject() {
		Response response = given().contentType("contentType.JSON").when().get("https://reqres.in/api/unknown/2");
		
		System.out.println("Body: " +  response.body().toString());
		JSONObject jsonObject = new JSONObject(response.asString());
		JSONObject data = jsonObject.getJSONObject("data");
		
		int id = data.getInt("id");
		int year = data.getInt("year");
		Assert.assertEquals(id, 2);
		Assert.assertEquals(year, 2001);
	}
}
