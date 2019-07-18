package com.rf.core;

import com.rf.apis.RestAPI;
import com.rf.util.JsonUtil;

public class RestEngine {
	
	public static void process(){
		RestAPI[] apis = readJsonRequestFile();
		
		for(RestAPI api:apis)
			api.sendRequest();
	}
	
	private static RestAPI[] readJsonRequestFile(){
		RestAPI[] restAPIs = (RestAPI[]) JsonUtil.getMappedObject("/jsonApis/test.json", RestAPI[].class);
		return restAPIs;
	}
	
	
	public static void main(String[] args) {
		//System.out.println(readJsonRequestFile()[0]);
		process();
	}

}
