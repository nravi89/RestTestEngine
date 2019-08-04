package com.rf.core;

import io.restassured.response.Response;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import com.rf.apis.Dconfig;
import com.rf.apis.RestAPI;
import com.rf.apis.RestApiFlow;
import com.rf.apis.handlers.ApiHandler;
import com.rf.util.JsonUtil;
import com.rf.util.RestUtil;

public class RestEngine {
	
	private static Logger logger = Logger.getLogger(RestEngine.class);
    private RestApiFlow apiFlow;
    private JtwigModel diCache = JtwigModel.newModel();
    private ApiHandler apiHandler;
	
	public RestEngine(String apiFlowPath) {
        this.apiFlow = readRestApiFlowFile(apiFlowPath);
         
        try {
			this.apiHandler = ApiHandler.initApiHAndler(apiFlow.getApiHandler());
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			logger.error("issue in initializing api handler",e);
		}
 		
	}
	
	public void start(){
		this.processAPIFlow();
	}
	
	private void processAPI(){
		RestAPI[] apis = readJsonRequestFile();
		for(RestAPI api:apis)
			api.sendRequest();
	}
	
	private void processAPIFlow(){
		if(logger.isInfoEnabled())
			logger.info("start process api flow for ::"+this.apiFlow);
		
		if(this.apiFlow == null){
			logger.error("API flow null - return");
			return;
		}
		
		HashMap<String, RestAPI> apis = apiFlow.getApis();
		for(String apiId:apiFlow.getSeq()){
			
			RestAPI api = processRequest(apis.get(apiId));
			
			if(apiHandler!=null)
				apiHandler.processRequest(api);
			
			Response resp = RestUtil.sendRequest(apiFlow.getBaseUri(),api);
			
			if(apiHandler!=null)
				apiHandler.processResponse(api, resp);
			
			if(apiFlow.getDi()!=null)
			processResponse(apiId, resp);
		}
	}
	
	private RestAPI processRequest(RestAPI api){
		
		if(logger.isDebugEnabled())
			logger.debug("processRequest called api::"+api);
		
		if(api==null)
			return null;
		

		injectValues(api.getQueryParams());		
		injectValues(api.getFormParams());
		injectValues(api.getPathParams());
		api.setBody(injectValues(api.getBody()));
		
		return api;
	}
	

	private  void processResponse(String apiId, Response resp) {
		   Dconfig[] dconfigs = apiFlow.getDi().get(apiId);
	       if(dconfigs == null)
	    	   return;
	       Object dvalue = null;
	      
	       for(Dconfig dc:dconfigs){
	    	   if(dc.getJsonPath()!=null){  //get data from resp body
	    		   dvalue = resp.getBody().jsonPath().get(dc.getJsonPath());
		    	   diCache.with(dc.getParamName(), dvalue);
		       }else if(dc.getHeader()!=null){  //get data from header
		    	   dvalue = resp.getHeader(dc.getHeader());
		    	   diCache.with(dc.getParamName(), dvalue);
		       }
	    	   
	    	   if(dvalue == null)
    			   logger.error("DI Failed - value:NULL for param name:"+dc.getParamName());
	       }
	}
	
	
    private String injectValues(String body) {
    	
    	if(body==null)
    		return null;
    	
    	JtwigTemplate template = JtwigTemplate.inlineTemplate(body);
        body = template.render(diCache);
        
        return body;
	}
	
	
	private void injectValues(HashMap<String, Object> raw){
		
		if(raw==null)
			return;
		
		raw.forEach((key,value)->{
			if(value instanceof String){
				String v = (String)value;
				if(v.indexOf("{{")>-1){
					JtwigTemplate template = JtwigTemplate.inlineTemplate(v);
			        v = template.render(diCache);
			        raw.put(key, v);
				}
			}
		});
	}
	

	private  RestAPI[] readJsonRequestFile(){
		RestAPI[] restAPIs =  (RestAPI[]) JsonUtil.getMappedObject("/jsonApis/test.json", RestAPI[].class);
		return restAPIs;
	}
	
	private RestApiFlow readRestApiFlowFile(String apiFlowPath){
		RestApiFlow restApiFlow = (RestApiFlow) JsonUtil.getMappedObject(apiFlowPath, RestApiFlow.class);
		
		if(logger.isDebugEnabled())
			logger.debug("Restapi flow::"+restApiFlow.getApis());
		
		return restApiFlow;
	}
	
	
	
	public static void main(String[] args) {
		//System.out.println(readJsonRequestFile()[0]);
	    RestEngine engine = new RestEngine("/jsonApis/test.json");
		engine.start();
		
		System.out.println(engine.diCache);
		
		/*HashMap<String, Object> respChache = new HashMap<String, Object>();
		respChache.put("name", "Ravi Narayan");
		
		JtwigTemplate template = JtwigTemplate.inlineTemplate("hello {{name}}");
        JtwigModel model = JtwigModel.newModel(respChache);

        String str = template.render(model);
        System.out.println(str);*/
		
	}

}
