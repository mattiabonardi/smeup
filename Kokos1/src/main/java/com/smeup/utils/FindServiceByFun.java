package com.smeup.utils;

public class FindServiceByFun {
	
	private String fun;
	
	public FindServiceByFun()
	{
		
	}
	
	public String find(String fun)
	{
		String[] app = fun.split(";");
		
		String service = app[1];
		
		return service;
	}

}
