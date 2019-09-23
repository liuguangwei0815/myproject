package com.quicky.demo.iplimit;

import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@Controller
@Scope("prototype")
@Slf4j
public class IpController {

	private static final String MESSAGE  =  "请求失败,你的IP访问太频繁";
	
	
	@RequestMapping("ipLimttest")
	@ResponseBody
	@PreAuthorize("hasAuthority('UserIndex')")
	@IpLimiter(limit = 10l,timet = 10, messge = MESSAGE)
	public String getReSoureceContr() {
		log.info("开始访问资源ipLimttest");
		return "good";
	} 
	
	@RequestMapping("ipLimttest1")
	@ResponseBody
	@PreAuthorize("hasAuthority('UserIndex')")
	@IpLimiter(limit = 10l,timet = 10, messge = MESSAGE)
	public String getReSoureceContr1() {
		log.info("开始访问资源getReSoureceContr1");
		return "good";
	} 
	
	
	
}
