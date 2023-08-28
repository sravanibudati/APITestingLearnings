package HttpRequests;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

public class RequestBodyDifferentWays {
	
	
	// Creating a Request Body using HashMAP
	@Test
	public void createUserWithHashMAP() {
		HashMap data = new HashMap();
		data.put("name","morpheus");
		data.put("job","leader"); 
		
		given()
			.contentType("application/json")
			.body(data)
		.when()
			.post("https://reqres.in/api/users")
		.then()
			.statusCode(201)
			.log().all();
	}
	
	// Creating a Request Body using JSONObject
	@Test
	public void createUserWithJSONObject() {
		JSONObject data = new JSONObject();
		data.put("name","morpheus");
		data.put("job","leader"); 
		
		given()
			.contentType("application/json")
			.body(data.toString())
		.when()
			.post("https://reqres.in/api/users")
		.then()
			.statusCode(201)
			.log().all();
	}
	
	// Creating a Request Body using POJO class
	@Test
	public void createUserWithPOJOClass() {
		User data = new User();
		data.setJob("QA Engineer");
		data.setName("Sravani");
		given()
			.contentType("application/json")
			.body(data)
		.when()
			.post("https://reqres.in/api/users")
		.then()
			.statusCode(201)
			.log().all();
		
	}
	
	// Creating a Request Body using External JSON File
		@Test
		public void createUserWithExternalJSON() throws FileNotFoundException {
			File file = new File(".\\user.json");
			FileReader reader = new FileReader(file);
			JSONTokener tokener = new JSONTokener(reader);
			JSONObject user = new JSONObject(tokener);
			
			given()
				.contentType("application/json")
				.body(user.toString())
			.when()
				.post("https://reqres.in/api/users")
			.then()
				.statusCode(201)
				.log().all();
			
		}

}
