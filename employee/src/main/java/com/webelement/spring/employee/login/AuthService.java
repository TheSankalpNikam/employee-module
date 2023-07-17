package com.webelement.spring.employee.login;

import org.springframework.stereotype.Service;

@Service
public class AuthService {
	
	public boolean authentication(String username, String password)
	{
		boolean isValidUsername = username.equalsIgnoreCase("Sankalp"); 
		boolean isValidpassword = password.equalsIgnoreCase("12345");
		
		return isValidUsername && isValidpassword;
	}
}
