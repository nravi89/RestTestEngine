package com.rf.apis;

import lombok.Data;

@Data
public class JsonPath {
	
	public static final String VALUE_TYPE_PRIMITIVE = "PRIM";
	public static final String VALUE_TYPE_JSON = "JSON";
	
	public static final String OPP_EQUALS = "EQ";
	public static final String OPP_EQUALS_IGNORE_CASE = "EIC";
	public static final String OPP_LESS_THAN = "LT";
	public static final String OPP_LESS_THAN_EQUALS = "LTE";
	public static final String OPP_GREATER_THAN = "GT";
	public static final String OPP_GREATER_THAN_EQUALS = "GTE";
	
	public static final String OPP_CONTAINS =  "CONTAINS";

	 private String key;
     private Object value;
     private String opp;
     private String valueType;

}
