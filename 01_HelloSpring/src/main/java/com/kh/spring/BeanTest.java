package com.kh.spring;

import org.springframework.stereotype.Component;

//@Component
public class BeanTest {

	public BeanTest() {
		// TODO Auto-generated constructor stub
	}
	
	
	public BeanTest(String name) {
		super();
		this.name = name;
	}


	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
		
}
