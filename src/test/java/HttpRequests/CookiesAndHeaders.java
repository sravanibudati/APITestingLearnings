package HttpRequests;
import static io.restassured.RestAssured.given;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class CookiesAndHeaders {
	
	@Test
	public void testCookies() {
		Response response = given().when().get("https://www.google.com");
		Map<String, String> cookies = response.getCookies();
		for (String key: cookies.keySet()) {
			String value = response.getCookie(key);
			System.out.println("Cookie : " + key + " and Value is " + value);
		}	
	}
	
	@Test
	public void testHeaders() {
		Response response = given().when().get("https://www.google.com");
		Headers headers = response.getHeaders();
		for (Header header: headers) {
			System.out.println("Header Name: " + header.getName() + " and Value: " + header.getValue());
		}
	}
}
