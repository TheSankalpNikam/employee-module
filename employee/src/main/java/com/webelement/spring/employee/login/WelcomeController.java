package com.webelement.spring.employee.login;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WelcomeController {
	
//	private AuthService authService;
//	
//	public LoginController(AuthService authService) {
//		super();
//		this.authService = authService;
//	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String gotoWelcomePage(ModelMap model)
	{
		model.put("name", getLoggedInUsername());
		return "welcome";
	}
	
	private String getLoggedInUsername()
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}
	
//	@RequestMapping(value = "login", method = RequestMethod.POST)
//	public String gotoWelcomePage(@RequestParam String name, @RequestParam String password, ModelMap map)
//	{
//		if (authService.authentication(name, password)) 
//		{
//			map.put("name", name);
//			map.put("password", password);
//			
//			return "welcome";
//		}
//		
//		map.put("error", "Invalid Credentials!");
//		return "login";
//	}
}
