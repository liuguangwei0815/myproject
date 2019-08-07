package com.quicky.demo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/user")
public class LoginController {
	@Autowired
	private StringRedisTemplate redisTemplate;

	@RequestMapping("/login")
	public Map<String, String> login(HttpServletRequest request, String account, String password) {
		String userId = "a123456";
		HttpSession session = request.getSession();
		session.setAttribute("loginUserId",userId);
		redisTemplate.opsForValue().set("loginUser:" + userId, session.getId());
		Map<String, String> returnMap = new HashMap<String, String>();
		returnMap.put("status", "200");
		returnMap.put("msg", "登录成功！");
		return returnMap;
	}

	@RequestMapping(value = "/getUserInfo")
	public Map<String, String> get(String userId) {
		Map<String, String> returnMap = new HashMap<String, String>();
		returnMap.put("status", "200");
		returnMap.put("msg", "查询成功！");
		return returnMap;
	}
}
