package HttpRequests;


import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;


public class QueryAndPathParams {

	@Test
	public void testQueryAndPathParams() {
		given()
			.pathParam("usersPath", "users")
			.queryParam("page", 2)
			.queryParam("id", 7)
			.contentType("application/json")
		.when()
			.get("https://reqres.in/api/{usersPath}")
		.then()
			.statusCode(200)
			.log().all();
	}
	
}
