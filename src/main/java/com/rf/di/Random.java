package com.rf.di;

import com.github.javafaker.Faker;

public interface Random {
	
	Faker FAKER = new Faker();
	
	public final Random  FULLNAME = new Random() {
		public String toString() {
			return FAKER.name().fullName();
		};
	};

	public final Random  FIRSTNAME = new Random() {
		public String toString() {
			return FAKER.name().firstName();
		};
	};
	
	public final Random  LASTNAME = new Random() {
		public String toString() {
			return FAKER.name().firstName();
		};
	};
	
	public final Random ADDRESS = new Random() {
	    public String toString() {
	    	return FAKER.address().fullAddress();
	    };
	}; 
	
	public static class NUMBER{
		
		public static Random digit(int digit){
			
			String s = "#";
			for(int i =1;i<digit;i++){
				s=s+"#";
			}
			
			final String pattern = s;
			return new Random() {
				public String toString() {	
					return FAKER.bothify(pattern,true);
				};
			};
			
		}
	};
}
