package com.quicky.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TestController1 {


	@RequestMapping("/test/hello") 
    public String hello(HttpServletRequest request, @RequestParam(value = "name", defaultValue = "springboot-thymeleaf") String name) { 
        request.setAttribute("name", name); 
        return "hello"; 
    } 
	@RequestMapping("/login")
	public String loginTest() {
		return "login";
	}

	

}