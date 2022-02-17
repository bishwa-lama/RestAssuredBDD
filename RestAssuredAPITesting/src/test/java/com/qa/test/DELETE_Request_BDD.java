package com.qa.test;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class DELETE_Request_BDD {
	public Properties prop;
	public String url = null;
	public String token = null;
	public String id = null;
	
	@BeforeTest
	public void initialize() throws IOException {
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
	public void deleteUserData() {
		given()
			.header("Authorization","Bearer "+token)
		.when().log().all().
			delete().
		then().log().all()
			.statusCode(204);
	}
}
