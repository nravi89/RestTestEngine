package com.rf.core;

import com.rf.apis.RestAPI;
import com.rf.util.JsonUtil;

public class RestEngine {
	
	public static void process(){
		
	}
	
	private static RestAPI[] readJsonRequestFile(){
		RestAPI[] restAPIs = (RestAPI[]) JsonUtil.getMappedObject("jsonApis/test.json", RestAPI.class);
		return restAPIs;
	}

}
