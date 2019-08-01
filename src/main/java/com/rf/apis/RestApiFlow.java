package com.rf.apis;

import java.util.HashMap;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestApiFlow {
	
	private String[] seq;
	private HashMap<String, String> properties;
	private HashMap<String, Dconfig[]> di;
	private HashMap<String, RestAPI> apis;
	

}
