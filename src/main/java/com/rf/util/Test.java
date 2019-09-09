package com.rf.util;

import com.github.javafaker.Faker;
import com.github.javafaker.HarryPotter;
import com.github.javafaker.Name;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Test {
	//http://localhost:8181/SpringExamples/get
	
	public static void main(String[] args) {
		/*Response response = RestAssured.given()
		          .baseUri("http://localhost:8181")
		                          .basePath("{name}/SpringExamples/{name}")
		                          .pathParam("name", "get")
		          		          .log().all().get();
		
		System.out.println(response);*/
		Faker faker = new Faker();
        Name name = faker.name();
		String fullName = name.fullName(); // Miss Samanta Schmidt
		String firstName = name.firstName(); // Emory
		String lastName = name.lastName(); // Barton

		String streetAddress = faker.address().fullAddress(); // 60018 Sawayn Brooks Suite 449
		
		System.out.println("name:"+fullName);
		System.out.println("firstName:"+firstName);
		System.out.println("last Name:"+lastName);
		System.out.println("User Name:"+name.username());
		System.out.println("address:"+streetAddress);
		System.out.println("digit:"+faker.bothify("##"));
		
		
		HarryPotter harryPotter = faker.harryPotter();
		System.out.println(harryPotter.character()+":"+harryPotter.quote());
	}

}
