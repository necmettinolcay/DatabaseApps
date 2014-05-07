package com.necmettin.servlet;
import javax.servlet.http.*;


public class CookieUtility {
		
	
	 public static String getCookieValue(HttpServletRequest request,
			 					String cookieName,
			 					String defaultValue) {
		 	Cookie[] cookies = request.getCookies();
		 	if (cookies != null) {
		 		for(Cookie cookie: cookies) {
		 			if (cookieName.equals(cookie.getName())) {
		 				return(cookie.getValue());
		 			}
		 		}
		 	}
		 	return(defaultValue);
}
	
	
	
	
}
