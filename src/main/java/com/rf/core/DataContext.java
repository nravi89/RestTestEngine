package com.rf.core;

import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import com.rf.di.Date;
import com.rf.di.Random;
import com.rf.di.Timestamp;

public class DataContext {
	
	 private JtwigModel diCache = JtwigModel.newModel();
	 
	 public DataContext() {
		diCache.with("DATE", Date.VALUE)
		       .with("TIMESTAMP", Timestamp.VALUE)
		       .with("R_FULL_NAME", Random.FULLNAME)
		       .with("R_FIRST_NAME",Random.FIRSTNAME)
		       .with("R_LAST_NAME", Random.LASTNAME)
		       .with("N1",Random.NUMBER.digit(1))
		       .with("N2",Random.NUMBER.digit(2))
		       .with("N3",Random.NUMBER.digit(3))
		       .with("N4",Random.NUMBER.digit(4))
		       .with("N5",Random.NUMBER.digit(5));
		       
		
	}
	 
	 public static DataContext getInstance(){
		 return new DataContext();
	 }
	 
	 public void addCache(String key, Object value){
		 diCache.with(key, value);
	 }
	 
	 public void addCache(String key, String value){
		 if(value.contains("{{"))
			 value = render(value);
		 diCache.with(key, value);
	 }
	 
	 public String render(String template){
		 JtwigTemplate t = JtwigTemplate.inlineTemplate(template);
	     return t.render(diCache);
	 }

}
