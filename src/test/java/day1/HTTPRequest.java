package day1;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import  org.testng.annotations.Test;

public class HTTPRequest {
	
	int id;
	
	@Test(priority = 1)
	public void getUser()
	{
		when()
		.get("https://reqres.in/api/users?page=2")
		.then()
		.statusCode(200)
		.body("page",equalTo(2))
		.log().all();
	}
	
	@Test(priority = 2)
	public void createUser()
	{
		HashMap data=new HashMap();
		data.put("name", "VDSTECH");
		data.put("job", "DevOps");
		
		id=given()
		.contentType("application/json")
		.body(data)
		.when()
		.post("https://reqres.in/api/users").jsonPath().getInt("id");
		
		//.then()
		//.statusCode(201)
		//.log().all();
	}
	
	@Test(priority = 3,dependsOnMethods = "createUser")
	public void updateUser()
	{
		HashMap data=new HashMap();
		data.put("name", "VDSTECH");
		data.put("job", "Architect");
		
		given()
		.contentType("application/json")
		.body(data)
		.when()
		.put("https://reqres.in/api/users/"+id)
		.then()
		.statusCode(200)
		.log().all();
	}
	
	@Test(priority = 4)
	public void deleteUser()
	{
		
		when().delete("https://reqres.in/api/users/"+id)
		.then()
		.statusCode(204)
		.log().all();
	}

}
