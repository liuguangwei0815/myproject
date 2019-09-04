package com.quicky.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

@RestController
@RequestMapping(value = "/")
public class TestController {

	@RequestMapping(value = "order/demo")
	public YYModel getDemo() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(auth);
		YYModel yy = new YYModel();
		yy.setYy("中文");
		yy.setZz(3);
		return yy;
	}

	@GetMapping("/test")
	public String getTest() {
		YYModel yy = new YYModel();
		yy.setYy("中文");
		yy.setZz(3);
		return JSON.toJSONString(yy);
	}
	
	@RequestMapping("/hello") 
    public String hello(HttpServletRequest request, @RequestParam(value = "name", defaultValue = "springboot-thymeleaf") String name) { 
        request.setAttribute("name", name); 
        return "hello"; 
    } 
	
	
	static class YYModel {

		String yy;

		Integer zz;

		public String getYy() {
			return yy;
		}

		public void setYy(String yy) {
			this.yy = yy;
		}

		public Integer getZz() {
			return zz;
		}

		public void setZz(Integer zz) {
			this.zz = zz;
		}

	}

}