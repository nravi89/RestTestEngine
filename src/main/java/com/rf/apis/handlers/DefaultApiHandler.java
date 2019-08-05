package com.rf.apis.handlers;


import static org.testng.Assert.assertEquals;

import org.apache.log4j.Logger;

import com.rf.apis.Assertion;
import com.rf.apis.JsonPath;
import com.rf.apis.RestAPI;
import com.rf.core.DataContext;
import com.rf.util.JsonUtil;

import io.restassured.internal.path.json.JSONAssertion;
import io.restassured.response.Response;
import net.javacrumbs.jsonunit.JsonAssert;
import net.javacrumbs.jsonunit.core.Configuration;
import net.javacrumbs.jsonunit.core.Option;
import net.javacrumbs.jsonunit.core.internal.Options;
import net.javacrumbs.jsonunit.core.util.ResourceUtils;


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
		
		Object expected = null;
		Object actual = resp.body().asString();
		if(logger.isInfoEnabled())
			logger.info("=======assertion start======");
		
		resp.then().assertThat().statusCode(assertion.getStatus());
		
		Configuration config = getAssertConfig(assertion.getChecks());
		
		//json value assertion 
		if(assertion.getJson()!=null){
			 expected = assertion.getJson();
			 JsonAssert.assertJsonEquals(expected, actual, config);
			 if(logger.isInfoEnabled())
					logger.info("=======json value assertion done======");
		}
		
		//json file assertion
		if(assertion.getJsonFile()!=null){
			expected = ResourceUtils.resource(assertion.getJsonFile());
			JsonAssert.assertJsonEquals(expected, actual, config);
			if(logger.isInfoEnabled())
				logger.info("=======json file assertion done======");
		}
		
		//json path assertion
		if(assertion.getJsonPaths()!=null){
			assertJsonPath(assertion.getJsonPaths(),resp,config);
		}
		
		//json path file assertion
		if(assertion.getJsonPathFile()!=null){
			
		}
		
		
		
		if(logger.isInfoEnabled())
			logger.info("=======assertion ends======");
	}
	
	
	
	private void assertJsonPath(JsonPath[] jsonPaths, Response resp, Configuration config){
		
		if(jsonPaths.length<0)
			return;
		
		for(JsonPath jsonPath:jsonPaths){
			Object actual = resp.body().jsonPath().get(jsonPath.getKey());
			
			if(jsonPath.getValueType().equals(JsonPath.VALUE_TYPE_PRIMITIVE))
			   assertEquals(actual, jsonPath.getValue());
			
			if(jsonPath.getValueType().equals(JsonPath.VALUE_TYPE_JSON))
			   JsonAssert.assertJsonEquals(jsonPath.getValue(), actual, config);
		}
		if(logger.isInfoEnabled())
			logger.info("=======json path assertion done======");
	}
	
	private void assertJsonPathFile(String jsonPathFile, Response resp, Configuration config){
		JsonPath[] jsonPaths = JsonUtil.getMappedObject(jsonPathFile, JsonPath[].class);
		assertJsonPath(jsonPaths, resp, config);
	}
	
	
	private Configuration getAssertConfig(String[] checks){
		Configuration config = Configuration.empty();
		int len = 0;
		Option[] ops = null;
		if(checks!=null)
			len = checks.length;
			
		if(len>0){
			ops = new Option[len];
			for(int i=0;i<len;i++){
				ops[i] = Option.valueOf(checks[i]);
			}
			
			config = config.when(Option.valueOf("IGNORING_ARRAY_ORDER"),ops);
		}else{
			config = config.when(Option.valueOf("IGNORING_ARRAY_ORDER"));
		}
		
		return config;
	}

}
