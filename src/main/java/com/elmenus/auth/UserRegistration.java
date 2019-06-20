package com.elmenus.auth;

import static helpers.ApiUtil.baseUri;
import static helpers.ApiUtil.contentTypeKey;
import static helpers.ApiUtil.contentTypeValue;
import static helpers.ApiUtil.stcSuccess;
import static helpers.ApiUtil.xClientIDHeaderKey;
import static helpers.ApiUtil.xClientIDHeaderValue;
import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.github.javafaker.Faker;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

public class UserRegistration {
	public UserRegistration() {
	}

	public UserRegistration(String test) {
		this();
	}

	@Test
	public void userRegisterWithValidData() {
		
		Faker faker = new Faker();
		String name = faker.name().fullName();
		String email = faker.internet().emailAddress();
		String password = faker.bothify("??????###");

		// Specify the base URL to the RESTful web service
		RestAssured.baseURI = baseUri;

		// Get the RequestSpecification of the request that you want to sent to the
		// server.
		RequestSpecification httpRequest = RestAssured.given();
		// Headers
		httpRequest.header(xClientIDHeaderKey, xClientIDHeaderValue);
		httpRequest.header(contentTypeKey, contentTypeValue);

		// JSONObject registerObject = new JSONObject();
		Map<String, String> user = new HashMap<>();

		// Register by using fake data
		user.put("name", name);
		user.put("email", email);
		user.put("password", password);
		httpRequest.body(user);
		
		// pass base path
		Response response = httpRequest.post("/user/register");
		int status = response.statusCode();
		assertEquals(stcSuccess, status);
		System.out.println(status);
		String body = response.getBody().asString();
		System.out.println(body);
		// System.out.println("Email "+email +" Pass "+password);
	}
}
