package com.quicky.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.quicky.demo.model.User;
import com.quicky.demo.service.UserService;
import com.quicky.licm.model.UserInfo;

@Controller
@Scope("prototype")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	
	
	@RequestMapping("/getUser")
	@ResponseBody
	public String getUser() {
		User user = userService.getUserbyuserName("Jaycekon");
		return JSONArray.toJSONString(user);
	}
	
	@RequestMapping("/getUserInfo")
	@ResponseBody
	public String getUserInfo() {
		UserInfo user = userService.getUserInfobyuserName("Jaycekon1");
		return JSONArray.toJSONString(user);
	}
	
	@RequestMapping("/insertUser")
	@ResponseBody
	public String insertUser() {
		return JSONArray.toJSONString(userService.createUser());
	}
	
	
	
}
