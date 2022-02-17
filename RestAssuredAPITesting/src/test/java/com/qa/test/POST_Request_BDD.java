package com.qa.test;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class POST_Request_BDD {
	public Properties prop;
	public static HashMap<String, String> map;
	public String url = null;
	public String token = null;
	
	@BeforeClass
	public void initialize() throws IOException {
		map = new HashMap<String, String>();
		map.put("name", RestUtils.getMaleName());
		map.put("email", RestUtils.getEmail());
		map.put("gender", RestUtils.genderMale());
		map.put("status", RestUtils.statusActive());
		
		prop = new Properties();
		//Retrieving data.properties file from resource location of this project using fileInputStream class
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\data.properties");
		prop.load(fis);
		url = prop.getProperty("goresturl");
		token = prop.getProperty("bearerToken");
		RestAssured.baseURI=url;
	}
	
	@Test
	public void postUserData() {
		given()
			.contentType("application/json")
			.body(map)
			.header("Authorization","Bearer "+token)
		.when().log().all().
			post().
		then().log().all()
			.statusCode(201)
			.header("Content-Type", "application/json; charset=utf-8");
	}
}
