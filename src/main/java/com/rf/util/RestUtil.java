package com.rf.util;

import java.util.HashMap;

import org.apache.log4j.Logger;

import com.rf.apis.RestAPI;

import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * 
 * @author rnarayan
 *
 */
public class RestUtil {
	
	private static Logger logger = Logger.getLogger(RestUtil.class);
	
	public static Response sendRequest(RestAPI restAPI){
		logger.info("sendRequest called for url:"+restAPI.getUrl());
		Response response = getRequestSpecification(restAPI).request(restAPI.getMethod(), restAPI.getUrl().trim());
		response.then().log().all();
		return response;
	}
	
	private static RequestSpecification getRequestSpecification(RestAPI restAPI){
	
		RequestSpecification requestSpecification = RestAssured.given().config(RestAssuredConfig.config()
				.encoderConfig(new EncoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false)));
    	
		requestSpecification.contentType(ContentType.JSON);
		
		HashMap<String,?> headers = restAPI.getHeaders();
		HashMap<String,?> queryParams = restAPI.getQueryParams();
		HashMap<String,?> formParams = restAPI.getFormParams();
		String body = restAPI.getBody();
		
    	if(headers!=null)
    	requestSpecification.headers(headers);
    	
    	if (queryParams != null)
		requestSpecification.queryParams(queryParams).log().all();
    	
		if (formParams != null)
		requestSpecification.formParams(formParams).log().all();

		if (body != null)
		requestSpecification.body(body).log().all();
	
    	
    	return requestSpecification.relaxedHTTPSValidation();

    }
	
}
