package com.qa.test;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class PUT_Request_BDD {
//3998
	
	public Properties prop;
	public static HashMap<String, String> map;
	public String url = null;
	public String token = null;
	public String id = null;
	
	@BeforeClass
	public void initialize() throws IOException {
		map = new HashMap<String, String>();
		map.put("gender", RestUtils.genderMale());
		map.put("status", RestUtils.statusInactive());
		
		prop = new Properties();
		//Retrieving data.properties file from resource location of this project using fileInputStream class
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\data.properties");
		prop.load(fis);
		url = prop.getProperty("goresturl");
		token = prop.getProperty("bearerToken");
		id = prop.getProperty("id");
		RestAssured.baseURI=url;
		RestAssured.basePath="/"+id;
	}
	
	@Test
	public void putUserData() {
		given()
			.contentType("application/json")
			.body(map)
			.header("Authorization","Bearer "+token)
		.when().log().all().
			put().
		then().log().all()
			.statusCode(200)
			.header("Content-Type", "application/json; charset=utf-8");
	}
}
