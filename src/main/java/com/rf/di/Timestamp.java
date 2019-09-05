package com.rf.di;

import java.util.Date;

/**
 * 
 * @author ravi narayan
 *
 */
public class Timestamp {
	
	public static final Timestamp VALUE = new Timestamp();
	
	private Timestamp() {}
	
	@Override
	public String toString() {
		return String.valueOf(new Date().getTime());
	}
}
