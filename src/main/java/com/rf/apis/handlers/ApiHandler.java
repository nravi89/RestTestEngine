package com.rf.apis.handlers;

import com.rf.apis.RestAPI;

public interface ApiHandler {
	public void preProcess(RestAPI restAPI);
	public void setDefaultHeaders(RestAPI restAPI);
}
