package com.rf.core;

import io.restassured.response.Response;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import com.rf.apis.Dconfig;
import com.rf.apis.RestAPI;
import com.rf.apis.RestApiFlow;
import com.rf.util.JsonUtil;

public class RestEngine {
	
	private static Logger logger = Logger.getLogger(RestEngine.class);
	//private Map<String, Object> diChache = new HashMap<String, Object>();
    private RestApiFlow apiFlow;
    private JtwigModel diChache = JtwigModel.newModel();
	
	public RestEngine(String apiFlowPath) {
         this.apiFlow = readRestApiFlowFile(apiFlowPath);
	}
	
	public void start(){
		this.processAPIFlow();
	}
	
	public void processAPI(){
		RestAPI[] apis = readJsonRequestFile();
		for(RestAPI api:apis)
			api.sendRequest();
	}
	
	private void processAPIFlow(){
		
		if(this.apiFlow == null){
			logger.error("API flow null - return");
			return;
		}
		
		HashMap<String, RestAPI> apis = apiFlow.getApis();
		for(String apiId:apiFlow.getSeq()){
			
			RestAPI api = processRequest(apis.get(apiId));
			
			Response resp = api.sendRequest();
			
			if(apiFlow.getDi()!=null)
			processResponse(apiId, resp);
		}
	}
	
	private RestAPI processRequest(RestAPI api){
		
		if(logger.isDebugEnabled())
			logger.debug("processRequest called api::"+api);
		
		if(api==null)
			return null;
		

		if(api.getQueryParams()!=null){
			injectValues(api.getQueryParams());
		}
		
		System.out.println("map2::"+api.getQueryParams());
		return api;
	}
	
	private  void processResponse(String apiId, Response resp) {
		   Dconfig[] dconfigs = apiFlow.getDi().get(apiId);
	       if(dconfigs == null)
	    	   return;
	       
	       //get data from resp body
	       for(Dconfig dc:dconfigs){
	    	   if(dc.getJsonPath()!=null){
		    	   diChache.with(dc.getParamName(), resp.getBody().jsonPath().get(dc.getJsonPath()));
		       }else if(dc.getHeader()!=null){
		    	   
		       }
	       }
	      
	}
	
	
	private void injectValues(HashMap<String, Object> raw){
		raw.forEach((key,value)->{
			if(value instanceof String){
				String v = (String)value;
				if(v.indexOf("{{")>-1){
					JtwigTemplate template = JtwigTemplate.inlineTemplate(v);
			        v = template.render(diChache);
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
		
		System.out.println(engine.diChache);
		
		/*HashMap<String, Object> respChache = new HashMap<String, Object>();
		respChache.put("name", "Ravi Narayan");
		
		JtwigTemplate template = JtwigTemplate.inlineTemplate("hello {{name}}");
        JtwigModel model = JtwigModel.newModel(respChache);

        String str = template.render(model);
        System.out.println(str);*/
		
	}

}
