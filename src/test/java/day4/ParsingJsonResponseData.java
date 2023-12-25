package day4;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ParsingJsonResponseData {
	
	@Test(priority = 1)
	void testJsonResponse()
	{
		// Approach -1 
	given()
			.contentType(ContentType.JSON)
			.when()
			  .get("https://reqres.in/api/users?page=2")
				.then()
				.statusCode(200)
				.header("Content-Type", "application/json; charset=utf-8")
				.body("data[0].first_name", equalTo("Michael"));
		
		
		// Approach - 2 :
		
		Response	res = given()
							.when()
								.get("https://reqres.in/api/users?page=2");
		
		Assert.assertEquals(res.statusCode(),200);  // Validation 1
		Assert.assertEquals("Content-Type", "application/json; charset=utf-8");
		
		String firstName = res.jsonPath().get("data[0].first_name").toString();
		Assert.assertEquals(firstName, "Michael");
	}
	
	@Test(priority = 1)
	void testJsonResponseBodyData()
	{
		// Approach - 2 :
		
				Response	res = given()
										.contentType(ContentType.JSON)
									.when()
										.get("https://reqres.in/api/users?page=2");
				
			/*	Assert.assertEquals(res.statusCode(),200);  // Validation 1
				Assert.assertEquals("Content-Type", "application/json; charset=utf-8");
				
				https://catfact.ninja/breeds
				https://reqres.in/api/users?page=2
				
				String firstName = res.jsonPath().get("data[0].first_name").toString();
				Assert.assertEquals(firstName, "Michael"); */
			
			// JSONObject class
			
			//JSONObject jo = new JSONObject(res.toString());
			
			JSONObject jo = new JSONObject(res.asString()); // Converting response to JSON Object
			
			// Validation of First_Name in Reqres API
			boolean status=false;
			for(int i=0;i<jo.getJSONArray("data").length();i++)
			{
				String firstName=jo.getJSONArray("data").getJSONObject(i).get("first_name").toString();
				System.out.println(firstName);
				
				if(firstName.equalsIgnoreCase("Tobias"))
				{
					status=true;
					break;
				}
			}
			
			Assert.assertEquals(status, true);
	}
	
	@Test(priority = 3)
	void testJsonResponseBodyData2()
	{
		Response	res = given()
				.contentType(ContentType.JSON)
			.when()
				.get("https://datausa.io/api/data?drilldowns=Nation&measures=Population");
		
		JSONObject jo = new JSONObject(res.asString()); // Converting response to JSON Object
		
		// Validation of Total population
					long totalPopulation=0;
					for(int i=0;i<jo.getJSONArray("data").length();i++)
					{
						String population=jo.getJSONArray("data").getJSONObject(i).get("Population").toString();
						totalPopulation=totalPopulation+Long.parseLong(population);
					}
					System.out.println("Total Population: "+totalPopulation);
					
					//Assert.assertEquals(totalPopulation, 2555891401);
					//Assert.assertEquals(totalPopulation, 2555891401,"Exact same");
					
					
	}

}
