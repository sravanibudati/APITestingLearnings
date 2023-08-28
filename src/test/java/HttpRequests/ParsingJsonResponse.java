package HttpRequests;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;
public class ParsingJsonResponse {
	
	
	
	@Test
	void jsonObject() {
		given()
			.contentType("contentType.JSON")
		.when()
			.get("https://reqres.in/api/unknown/2")
		.then()
			.log().all();

	}

}
