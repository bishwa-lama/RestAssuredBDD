package com.qa.test;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class GET_Request_BDD {
	public Properties prop;
	public String url = null;
	public String path = null;
	public String key = null;
	public String city = null;
	
	@BeforeTest
	public void initialize() throws IOException {
		prop = new Properties();
		//Retrieving data.properties file from resource location of this project using fileInputStream class
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\data.properties");
		prop.load(fis);
		url = prop.getProperty("weatherurl");
		path = prop.getProperty("weatherbasepath");
		key = prop.getProperty("key");
		city = prop.getProperty("city");
	}
	
	@Test
	public void getWeatherDetailsofCity() {
		given().
		when().log().all().
			get(url+key+path+city).
		then().log().all()
			.statusCode(200)
			.assertThat().body("location.name", equalTo(city))
			.header("Content-Type", "application/json");
	}
}
