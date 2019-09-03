package com.rf.util;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

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
	
	public static Response sendRequest(String baseUri, RestAPI restAPI){
		
		if(restAPI.getBaseUri()!=null)
			baseUri = restAPI.getBaseUri();
		
		Response response = getRequestSpecification(restAPI).baseUri(baseUri)
				                                            .basePath(restAPI.getBasePath())
				                                            .request(restAPI.getMethod());
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
		HashMap<String,?> pathParams = restAPI.getPathParams();
		JSONObject body = restAPI.getBody();
		
		if(pathParams!=null)
		requestSpecification.pathParams(pathParams);	
			
    	if(headers!=null)
    	requestSpecification.headers(headers);
    	
    	if (queryParams != null)
		requestSpecification.queryParams(queryParams);
    	
		if (formParams != null)
		requestSpecification.formParams(formParams);

		if (body != null)
		requestSpecification.body(body);
		
		requestSpecification.log().all();
    	
    	return requestSpecification.relaxedHTTPSValidation();

    }
	
}
