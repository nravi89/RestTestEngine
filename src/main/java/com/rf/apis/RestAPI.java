package com.rf.apis;

import io.restassured.response.Response;
import java.util.HashMap;

import org.json.simple.JSONObject;

import lombok.Data;

import com.rf.core.DataContext;
import com.rf.util.JsonUtil;
import com.rf.util.RestUtil;


/**
 * @author ravinarayan
 * Default RestAPI implementation for rest framework 
 */
@Data
public class RestAPI{

	 private String baseUri;
	 private String basePath;
	 private String method;
	 private HashMap<String,Object> pathParams;
	 private HashMap<String,Object> headers;
	 private HashMap<String,Object> queryParams;
	 private HashMap<String,Object> formParams;
	 private JSONObject body;
	 private Assertion assertion;
	
	 public RestAPI() {}
	 
	 public RestAPI(String baseUri) {
			this.baseUri = baseUri;
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
	
/*	private void buildUrl(){		
			if(pathParams==null)
				return;
			
			pathParams.forEach((k,v)->{
				url = url.replace(k, v);
			});
	}
	*/
	
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
		this.body = JsonUtil.getJsonObject(body);
		return this;
	}
	
	public Response sendRequest(){	
		return RestUtil.sendRequest(null,this);
	}

	
	public void inject(DataContext context){
		
		if(baseUri!=null && baseUri.indexOf("{{")>-1){
			baseUri = context.render(baseUri);
		}
		
		if(basePath!=null && basePath.indexOf("{{")>-1){
			basePath = context.render(basePath);
		}
		
		injectValues(headers, context);
		injectValues(queryParams, context);		
		injectValues(formParams,context);
		injectValues(pathParams, context);
		injectBody(context);
	}
	
	
    private void injectBody(DataContext context) {
    	
    	if(body==null)
    		return;
    
    	String bodyStr = this.body.toJSONString();
    	if(bodyStr.indexOf("{{")!=-1){
    		bodyStr = context.render(bodyStr);
            this.body =  JsonUtil.getJsonObject(bodyStr);	
    	}
	}
	
	
	private void injectValues(HashMap<String, Object> raw, DataContext context){
		
		if(raw==null)
			return;
		
		raw.forEach((key,value)->{
			if(value instanceof String){
				String v = (String)value;
				if(v.indexOf("{{")>-1){
					v = context.render(v);
			        raw.put(key, v);
				}
			}
		});
	}
}
