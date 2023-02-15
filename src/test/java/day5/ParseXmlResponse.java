package day5;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;


public class ParseXmlResponse {
	
	//@Test
	void testXmlResponse()
	{
		//Appraoch-1
		
		/*given()
			.when()
				.get("http://restapi.adequateshop.com/api/Traveler")
				.then()
				.statusCode(200)
				.header("Content-Type", "application/xml; charset=utf-8")
				.body("TravelerinformationResponse.page", equalTo("1"))
				.body("TravelerinformationResponse.travelers.Travelerinformation[0].name", equalTo("Developer"));
			*/
		
		// Approach-2
		
		Response res=given()
			.when()
				.get("http://restapi.adequateshop.com/api/Traveler");
		
		Assert.assertEquals(res.statusCode(), 200);
		Assert.assertEquals(res.header("Content-Type"), "application/xml; charset=utf-8");
		
		String pageNo = res.xmlPath().get("TravelerinformationResponse.page").toString();
		Assert.assertEquals(pageNo, "1");
		
		String travelerName = res.xmlPath().get("TravelerinformationResponse.travelers.Travelerinformation[0].name").toString();
		Assert.assertEquals(travelerName, "Developer");
	}
	
	@Test
	void pareseXmlResponseBody()
	{
		Response res=given()
				.when()
					.get("http://restapi.adequateshop.com/api/Traveler?page=1");
			
			XmlPath xmlObj=new XmlPath(res.asString());
			
		List<String> traveller =xmlObj.getList("TravelerinformationResponse.travelers.Travelerinformation");
		Assert.assertEquals(traveller.size(), 10);
		
		//Verify Traveller name present in response:
		List<String> traveller_name =xmlObj.getList("TravelerinformationResponse.travelers.Travelerinformation.name");
		
		for(String travellerName : traveller_name )
		{
			boolean status=false;
			System.out.println(travellerName);
			if(travellerName.equals("AS"))
			{
				status=true;
				break;
			}
			Assert.assertEquals(status, true);
		}
		
		
			
	}

}
