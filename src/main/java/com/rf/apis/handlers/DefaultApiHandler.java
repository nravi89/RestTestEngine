package com.rf.apis.handlers;

import com.rf.apis.RestAPI;
import com.rf.configurations.Config;

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


}
