package com.rf.core;

import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

public class DataContext {
	
	 private JtwigModel diCache = JtwigModel.newModel();
	 
	 
	 public static DataContext getInstance(){
		 return new DataContext();
	 }
	 
	 public void addCache(String key, Object value){
		 diCache.with(key, value);
	 }
	 
	 public String render(String template){
		 JtwigTemplate t = JtwigTemplate.inlineTemplate(template);
	     return t.render(diCache);
	 }

}
