package com.vtw.portal.auth.authorization.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

	@GetMapping("/")
    public String index(){
        return "index";
    }
	
    @GetMapping("/login")
    public String login(){
    	return "LoginPage";
    }
}