package day5;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

public class FileUploadAndDownload {
	
	@Test
	void singleFileUpload()
	{
		File myFile = new File("D:\\VANU\\API\\SDET- Postman\\Test1.txt");
		given()
			.multiPart("file",myFile)
			.contentType("multipart/form-data")
			
			.when()
				.post("http://localhost:8080/uploadFile")
				.then()
				.statusCode(200)
				.body("fileName", equalTo("Test1.txt.txt"))
				.log().all();
	}

}
