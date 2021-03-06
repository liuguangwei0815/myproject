package com.quicky.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.quicky.demo.service.UserService;
import com.quicky.licm.model.UserInfo;

@Controller
@Scope("prototype")
//这里面的属性有可能会更新的，git中的配置中心变化的话就要刷新，没有这个注解内，配置就不能及时更新
@RefreshScope
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	
	
	@RequestMapping("/getUser")
	@ResponseBody
	public String getUser() {
		//User user = userService.getUserbyuserName("Jaycekon");
		//return JSONArray.toJSONString(getUserInfo());
		return "accout service";
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
	
	@Value("${name1}")
    private String name;
    @Value("${password1}")
    private String password;
    @Value("${age}")
    private String age;
    /**
     * 通过配置中心获取参数
     * @return
     */
	@RequestMapping("/getConfigVal")
	@ResponseBody
	public String getConfigVal() {
		return JSON.toJSONString(name+":"+password+":"+age);
	}
	
	
}
