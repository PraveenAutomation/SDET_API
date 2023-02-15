package day2;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONTokener;
import  org.testng.annotations.Test;


/*
 => Different Ways to create POST Request Body:
		1. Using HashMap
		2. Using org.json
		3. Using POJO (Plain Old Java Object)
		4. Using JSON External File  
 */

public class DiffWaysToCreatePostRequestBody {
	
	// 1. POST Request body Using HashMap
	//@Test
	void postBodyUsingHashMap()
	{
		HashMap data = new HashMap();
		
		data.put("name","morpheus");
		data.put("job", "leader");
		
		given()
		.contentType("application/json")
		.body(data)
		
		.when()
		.post("https://reqres.in/api/users")
		
		
		.then()
		.statusCode(201)
		.body("name", equalTo("morpheus"))
		.body("job", equalTo("leader"))
		.header("Content-Type", "application/json; charset=utf-8")
		.log().all();
	}
	
	//2.Post Request Body  Using org.json
	//@Test
	void postRequestBodyByJsonLib()
	{
		JSONObject data = new JSONObject();
		
		data.put("name","morpheus");
		data.put("job", "leader");
		
		given()
		.contentType("application/json")
		.body(data.toString())
		
		.when()
		.post("https://reqres.in/api/users")
		
		
		.then()
		.statusCode(201)
		.body("name", equalTo("morpheus"))
		.body("job", equalTo("leader"))
		.header("Content-Type", "application/json; charset=utf-8")
		.log().all();
	}
	
	//3. Post Request Body Using POJO (Plain Old Java Object)
	//@Test
	void postRequestBodyByPOJO()
	{
		POJO_PostRequest data = new POJO_PostRequest();
		
		data.setName("morpheus");
		data.setJob("leader");

		
		given()
		.contentType("application/json")
		.body(data)
		
		.when()
		.post("https://reqres.in/api/users")
		
		
		.then()
		.statusCode(201)
		.body("name", equalTo("morpheus"))
		.body("job", equalTo("leader"))
		.header("Content-Type", "application/json; charset=utf-8")
		.log().all();
	}
	
	//	4. Post Request Body Using JSON External File  
	@Test
	void postRequestBodyByExternalJsonFile() throws FileNotFoundException
	{
		File f=new File(".\\postBody.json");
		
		FileReader fr = new FileReader(f);
		
		JSONTokener jt = new JSONTokener(fr);
		
		JSONObject data = new JSONObject(jt);
		
		given()
		.contentType("application/json")
		.body(data.toString())
		
		.when()
		.post("https://reqres.in/api/users")
		
		
		.then()
		.statusCode(201)
		.body("name", equalTo("morpheus"))
		.body("job", equalTo("leader"))
		.header("Content-Type", "application/json; charset=utf-8")
		.log().all();
	}


}
