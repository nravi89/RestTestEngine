package com.rf.apis;

import lombok.Data;

@Data
public class Assertion {
	
	  private int status;
	  private String[] checks;
	  private Object json;
	  private String jsonFile;
	  private String jsonPathFile;
	  private JsonPath[] jsonPaths;

}
