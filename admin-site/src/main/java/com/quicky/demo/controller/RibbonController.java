package com.quicky.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quicky.demo.service.RibbonService;

@Controller
@Scope("prototype")
public class RibbonController {

	@Autowired
	RibbonService ribbonService;

	@RequestMapping("/hello")
	@ResponseBody
	public String customer() {
		return ribbonService.getUserInfo();
	}
	
	
	@RequestMapping("/postget")
	@ResponseBody
	public String post() {
		return ribbonService.getUserInfoPost("AsPect my");
	}

}
