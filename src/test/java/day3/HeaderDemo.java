package day3;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class HeaderDemo {
	
	@Test(priority = 1)
	void testHeaders()
	{
		given()
			.when()
			.get("https://www.google.co.in/")
				.then()
					.header("Content-Type", "text/html; charset=ISO-8859-1")
					.and()
					.header("Content-Encoding", "gzip")
					.header("Server","gws");
	}
	
	@Test(priority = 2)
	void getHeaders()
	{
		Response res=given()
			.when()
			   .get("https://www.google.co.in/");
		
		// Get single header info:
		String header_value1=res.header("Content-Type");
		System.out.println("Content-Type  header value : "+header_value1);
		
		// Get all headers info
		
		Headers headers_vlaues = res.getHeaders();
		
		for(Header hd : headers_vlaues )
		{
			System.out.println(hd.getName()+" : "+hd.getValue());
		}
	}
	
	

}
