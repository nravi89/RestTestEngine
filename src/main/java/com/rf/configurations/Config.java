package com.rf.configurations;

import java.io.IOException;
import java.util.Properties;

public class Config {
	
	private static Properties properties;
	
	static{
		properties = new Properties();
		try {
			properties.load(Config.class.getResourceAsStream("base.config"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getProperty(String key){
		return (String) properties.get(key);
	}

}
