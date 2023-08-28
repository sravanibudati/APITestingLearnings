package HttpRequests;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
public class ReqRes {
       
	private int userId;
	
	@Test(priority = 1, description = "To Test Create User API")
	void createUser() {
		
		HashMap<String, String> data = new HashMap<String, String>();
		data.put("name","morpheus");
		data.put("job","leader"); 
		
		userId = given()
			.contentType("application/json")
			.body(data)
		.when()
			.post("https://reqres.in/api/users")
			.jsonPath().getInt("id");
	}
	
	@Test(priority = 2, description = "To GET Users API")
	void getUsers() {
		
		given()
		.when()
			.get("https://reqres.in/api/users?page=2")
		.then()
			.statusCode(200)
			.body("page",equalTo(2))
			.log().all();
	}
	
	@Test(priority = 3, dependsOnMethods = {"createUser"}, description = "To Test Update User API")
	void updateUser() {
		HashMap data = new HashMap();
		data.put("name","morpheus updated");
		data.put("job","team lead"); 
		
		given()
			.contentType("application/json")
			.body(data)
		.when()
			.put("https://reqres.in/api/users/" + userId)
		.then()
			.statusCode(200)
			.body("name", equalTo("morpheus updated"))
			.body("job", equalTo("team lead"))
			.log().all();
	}

}
