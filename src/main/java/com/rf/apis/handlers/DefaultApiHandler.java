package com.rf.apis.handlers;

import io.restassured.response.Response;

import com.rf.apis.RestAPI;
import com.rf.configurations.Config;
import com.rf.util.RestUtil;

/**
 * 
 * @author ravinarayan
 *
 */
public class DefaultApiHandler implements ApiHandler{
	
	@Override
	public void preProcess(RestAPI restAPI) {
		System.out.println("DefaultApiHandler preprocess called");
	}

	public Response sendRequest(RestAPI restAPI){
		this.preProcess(restAPI);
		return restAPI.sendRequest();
	} 

}
