package com.qa.authConcept;

import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;


public class Base {
	
	@BeforeClass
	public void setUp()
	{
		RestAssured.authentication=RestAssured.preemptive().basic("postman", "password");
		RestAssured.baseURI="https://postman-echo.com/basic-auth";
	}

}
