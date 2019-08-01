package com.rf.util;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Test {
	//http://localhost:8181/SpringExamples/get
	
	public static void main(String[] args) {
		Response response = RestAssured.given()
		          .baseUri("http://localhost:8181")
		                          .basePath("{name}/SpringExamples/{name}")
		                          .pathParam("name", "get")
		          		          .log().all().get();
		
		System.out.println(response);
	}

}
