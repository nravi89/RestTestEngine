package com.rf.apis.handlers;

import com.rf.apis.RestApiFlow;

/**
 * 
 * @author ravinarayan
 *
 */
public interface FlowHandler {
	
	void preEvent(RestApiFlow apiFlow);
	void postEvent(RestApiFlow apiFlow); 

}
