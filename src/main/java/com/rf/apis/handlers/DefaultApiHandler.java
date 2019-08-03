package com.rf.apis.handlers;


import com.rf.apis.RestAPI;

import io.restassured.response.Response;


/**
 * 
 * @author ravinarayan
 *
 */
public class DefaultApiHandler implements ApiHandler{
	
	@Override
	public void processRequest(RestAPI restAPI) {
		System.out.println("DefaultApiHandler preprocess called");
	}

	@Override
	public void processResponse(RestAPI restAPI, Response resp) {
		// TODO Auto-generated method stub
		
	}

}
