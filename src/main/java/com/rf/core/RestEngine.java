package com.rf.core;

import java.util.HashMap;

import com.rf.apis.RestAPI;
import com.rf.apis.RestApiFlow;
import com.rf.util.JsonUtil;

public class RestEngine {
	
	public static void processAPI(){
		RestAPI[] apis = readJsonRequestFile();
		
		for(RestAPI api:apis)
			api.sendRequest();
	}
	
	public static void processAPIFlow(){
		RestApiFlow apiFlow = readRestApiFlowFile();
		HashMap<String, RestAPI> apis = apiFlow.getApis();
		for(String apiId:apiFlow.getSeq())
			apis.get(apiId).sendRequest();
	}
	
	
	
	private static RestAPI[] readJsonRequestFile(){
		RestAPI[] restAPIs = (RestAPI[]) JsonUtil.getMappedObject("/jsonApis/test.json", RestAPI[].class);
		return restAPIs;
	}
	
	private static RestApiFlow readRestApiFlowFile(){
		RestApiFlow restApiFlow = (RestApiFlow) JsonUtil.getMappedObject("/jsonApis/test.json", RestApiFlow.class);
		return restApiFlow;
	}
	
	
	public static void main(String[] args) {
		//System.out.println(readJsonRequestFile()[0]);
		processAPIFlow();
	}

}
