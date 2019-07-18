package com.rf.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
	
private static Logger logger = Logger.getLogger(JsonUtil.class);
	
	public static Object getMappedObject(String  jsonFilePath,Class classType){
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			//File file = new File(jsonFilePath);
			InputStream file = JsonUtil.class.getResourceAsStream(jsonFilePath);
			Object obj = objectMapper.readValue(file,classType);
		    //objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		   // objectMapper.setVisibilityChecker(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
		    return obj;
		} catch (IOException e) {
			e.printStackTrace();
		    logger.error("issue in getMappedObject",e);
		}
		return null;
	}
	
	public static String getJsonString(Object obj){
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonStr = null;
		try {
			jsonStr = objectMapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			logger.error("issue in getJsonString",e);
		}
	    return jsonStr;
	}

}
