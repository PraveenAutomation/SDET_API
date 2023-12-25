package com.qa.authConcept;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class RestAssuredAuthDemo extends Base{
	
	@Test
	public void authTest()
	{
		int code=RestAssured.given()
						.get()
						.getStatusCode();
		
		System.out.println("Status code is "+code);
	}

}
