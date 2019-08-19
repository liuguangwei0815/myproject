package com.quicky.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.quicky.demo.service.RibbonService;

@Service
public class RibbonServiceImpl implements RibbonService{
	
	@Autowired
	RestTemplate restTemplate;
	
	
	public String getUserInfoErro() {
		return "Error occurre熔断错误!";
	}

	@Override
	@HystrixCommand(fallbackMethod = "getUserInfoErro")
	public String getUserInfo() {
		return  restTemplate.getForObject("http://user-service/getUser", String.class);
	}

}
