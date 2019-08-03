package com.rf.apis.handlers;

import com.rf.apis.RestAPI;

import io.restassured.response.Response;

/**
 * 
 * @author ravi narayan
 *
 */
public interface ApiHandler {
	public void processRequest(RestAPI restAPI);
	public void processResponse(RestAPI restAPI, Response resp);
	
	public static ApiHandler initApiHAndler(String classPath) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		if(classPath==null)
			return null;
		
		ApiHandler apiHandler = (ApiHandler) Class.forName(classPath).newInstance();
		return apiHandler;
	}
}
