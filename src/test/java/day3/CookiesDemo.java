package day3;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class CookiesDemo {
	
	@Test(priority = 1)
	void testCookies()
	{
		given()
		
			.when()
				.get("https://www.google.co.in/")
			
				.then()
					.cookie("AEC","ARSKqsKxNWGZ1J2lxHN8kRQL4tcH6znxikkjutT36cRJ1fwD_2ZBEDMlPA")
					.statusCode(200)
					.log().all();
	}
	
	@Test(priority = 2)
	void getCookies()
	{
		Response res= when()
						.get("https://www.google.co.in/");
		
		// Get Single Cookie info
		String cookie_value1=res.getCookie("AEC");
		System.out.println("Cookie value is ==> "+cookie_value1);
		
		//Get all cookies info:
		Map<String, String> cookie_values = res.cookies();
		//System.out.println(cookie_values.keySet());
		
		for(String k : cookie_values.keySet()) 
		{
			String cookie_value = res.getCookie(k);
			System.out.println(k+" : "+cookie_value);
		}
		
			

	}

}
