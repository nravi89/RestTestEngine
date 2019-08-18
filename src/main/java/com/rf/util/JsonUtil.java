package com.rf.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.javacrumbs.jsonunit.core.util.ResourceUtils;

public class JsonUtil {
	
private static Logger logger = Logger.getLogger(JsonUtil.class);
	
	public static <T> T getMappedObject(String  jsonFilePath,Class<T> classType){
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			//File file = new File(jsonFilePath);
			//InputStream file = JsonUtil.class.getResourceAsStream(jsonFilePath);
			Reader reader = ResourceUtils.resource(jsonFilePath);
			
			T obj = objectMapper.readValue(reader,classType);
		    //objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		   // objectMapper.setVisibilityChecker(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
		    return obj;
		} catch (IOException e) {
			e.printStackTrace();
		    logger.error("issue in getMappedObject",e);
		}
		return null;
	}
	
	public static JSONObject getJsonObject(String jsonStr){
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			//File file = new File(jsonFilePath);
			//InputStream file = JsonUtil.class.getResourceAsStream(jsonFilePath);
			//Reader reader = ResourceUtils.resource(jsonFilePath);
			
			JSONObject obj = objectMapper.readValue(jsonStr,JSONObject.class);
		    //objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		   // objectMapper.setVisibilityChecker(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
		    return obj;
		} catch (IOException e) {
			e.printStackTrace();
		    logger.error("issue in getJsonObject",e);
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
