package com.necmettin.servlet;

import javax.servlet.http.*;

public class LivedCookie extends Cookie {
	public static final int THREE_DAY = 60*60*24*3;

	public LivedCookie(String name, String value) {
		super(name,value );
		setMaxAge(THREE_DAY);
		
	}

	
	

}
