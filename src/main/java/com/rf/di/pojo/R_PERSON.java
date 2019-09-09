package com.rf.di.pojo;

import com.rf.core.DataContext;
import com.rf.di.R_POJO;
import com.rf.di.Random;

public class R_PERSON implements R_POJO{
	
	private String name;
	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private String pincode;
	private String email;
	private String mobile;

	@Override
	public void build(DataContext context) {
		String firstName = Random.FAKER.name().firstName();
	}
}
