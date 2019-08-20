package com.rf.apis.handlers;

import com.rf.apis.RestAPI;
import com.rf.core.DataContext;

import io.restassured.response.Response;

/**
 * 
 * @author ravi narayan
 *
 */
public interface ApiHandler {
	
	public void processRequest(RestAPI restAPI, DataContext context);
	public void processResponse(RestAPI restAPI, Response resp, DataContext context);
	
	public static ApiHandler initApiHandler(String classPath) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		if(classPath==null)
			return null;
		
		ApiHandler apiHandler = (ApiHandler) Class.forName(classPath).newInstance();
		return apiHandler;
	}
}
