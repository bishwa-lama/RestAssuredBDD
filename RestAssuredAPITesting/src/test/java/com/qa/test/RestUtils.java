package com.qa.test;

import org.apache.commons.lang3.RandomStringUtils;
import org.joda.time.Instant;

public class RestUtils {
	
	public static String getMaleName() {
		return ("Mike"+ " Tyson"+Instant.now().toString());
	}
	
	public static String getFemaleName() {
		return ("Emma"+ " Smith"+Instant.now().toString());
	}
	
	public static String getEmail() {
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return (generatedString+"@gmail.com");
	}
	
	public static String genderMale() {
		return ("Male");
	}
	
	public static String genderFemale() {
		return ("Male");
	}
	
	public static String statusActive() {
		return ("Active");
	}
	
	public static String statusInactive() {
		return ("Inactive");
	}
}
