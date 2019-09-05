package com.rf.di;

import java.text.SimpleDateFormat;

/**
 * 
 * @author ravi narayan
 *
 */
public class Date {
	
	private SimpleDateFormat dateFormat;
	public static final Date VALUE = new Date();
	
	private Date() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
	}

	private Date(String pattern){
		dateFormat = new SimpleDateFormat(pattern);
	}
	
	@Override
	public String toString() {
		return dateFormat.format(new Date());
	}
	
	
	public static Date getInstance(String pattern){
		return new Date(pattern);
	}
	
}
