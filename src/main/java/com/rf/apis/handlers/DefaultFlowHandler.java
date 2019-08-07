package com.rf.apis.handlers;

import com.rf.apis.RestApiFlow;

public class DefaultFlowHandler implements FlowHandler{

	@Override
	public void preEvent(RestApiFlow apiFlow) {
		System.out.println("api flow pre check");
	}

	@Override
	public void postEvent(RestApiFlow apiFlow) {
		System.out.println("api flow post check");
	}

}
