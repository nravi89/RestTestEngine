package com.rf.apis;

import io.restassured.response.Response;
import java.util.HashMap;
import lombok.Data;
import com.rf.util.RestUtil;


/**
 * @author rnarayan
 * Default RestAPI implementation for rest framework 
 */
@Data
public class RestAPI{

	 private String url;
	 private String method;
	 private HashMap<String,String> pathParams;
	 private HashMap<String,Object> headers;
	 private HashMap<String,Object> queryParams;
	 private HashMap<String,Object> formParams;
	 private String body;
	
	 public RestAPI() {}
	 
	 public RestAPI(String url) {
			this.url = url;
	}
	
	public final RestAPI header(String key, Object value) {
		headers.put(key, value);
		return this;
	}
	
	
	public final RestAPI pathParam(String key, String value) {
		if(pathParams==null)
			pathParams = new HashMap<>();
		
		pathParams.put(key, value);
		
		return this;
	}
	
	private void buildUrl(){		
			if(pathParams==null)
				return;
			
			pathParams.forEach((k,v)->{
				url = url.replace(k, v);
			});
	}
	
	
	public RestAPI queryParam(String key, Object value) {
		if(queryParams == null)
			queryParams = new HashMap<>();
		
		queryParams.put(key, value);
		
		return this;
	}
	
	
	public RestAPI formParam(String key, Object value) {
		if(formParams==null)
			formParams = new HashMap<>();
		
		formParams.put(key, value);
		
		return this;
	}
	
	
	public RestAPI body(String body) {
		this.body = body;
		return this;
	}
	
	public Response sendRequest(){
		return RestUtil.sendRequest(this);
	}

}
