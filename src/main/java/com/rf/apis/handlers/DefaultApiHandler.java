package com.rf.apis.handlers;


import org.apache.log4j.Logger;

import com.rf.apis.Assertion;
import com.rf.apis.RestAPI;
import com.rf.core.DataContext;

import io.restassured.internal.path.json.JSONAssertion;
import io.restassured.response.Response;
import net.javacrumbs.jsonunit.JsonAssert;
import net.javacrumbs.jsonunit.core.Configuration;
import net.javacrumbs.jsonunit.core.Option;
import net.javacrumbs.jsonunit.core.internal.Options;


/**
 * 
 * @author ravinarayan
 *
 */
public class DefaultApiHandler implements ApiHandler{
	
	private static final Logger logger = Logger.getLogger(DefaultApiHandler.class);
	
	@Override
	public void processRequest(RestAPI restAPI, DataContext context) {
		System.out.println("DefaultApiHandler preprocess called");
	}

	@Override
	public void processResponse(RestAPI restAPI, Response resp, DataContext context) {
		Assertion assertion = restAPI.getAssertion();
		if(assertion==null)
			return;
		
		if(logger.isInfoEnabled())
			logger.info("=======assertion start======");
		
		resp.then().assertThat().statusCode(assertion.getStatus());
		
		Object expected = restAPI.getAssertion().getJson();
		Object actual = resp.body().asString();
		Configuration config = Configuration.empty();
		String[] checks = assertion.getChecks();
		int len = 0;
		Option[] ops = null;
		if(checks!=null)
			len = assertion.getChecks().length;
			
		if(len>0){
			ops = new Option[len];
			for(int i=0;i<len;i++){
				ops[i] = Option.valueOf(checks[i]);
			}
			
			config = config.when(Option.valueOf("IGNORING_ARRAY_ORDER"),ops);
		}else{
			config = config.when(Option.valueOf("IGNORING_ARRAY_ORDER"));
		}
		
		
		JsonAssert.assertJsonEquals(expected, actual, config);
		if(logger.isInfoEnabled())
			logger.info("=======assertion ends======");
	}

}
