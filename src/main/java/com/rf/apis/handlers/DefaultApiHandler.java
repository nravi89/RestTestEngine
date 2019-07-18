package com.rf.apis.handlers;

import io.restassured.response.Response;

import com.rf.apis.RestAPI;
import com.rf.configurations.Config;
import com.rf.util.RestUtil;

/**
 * 
 * @author rnarayan
 *
 */
public class DefaultApiHandler implements ApiHandler{

	@Override
	public void setDefaultHeaders(RestAPI restAPI) {
	  
	}
	
	@Override
	public void preProcess(RestAPI restAPI) {
		this.setDefaultHeaders(restAPI);
	}

	public Response sendRequest(RestAPI restAPI){
		this.preProcess(restAPI);
		return RestUtil.sendRequest(restAPI);
	} 

}
