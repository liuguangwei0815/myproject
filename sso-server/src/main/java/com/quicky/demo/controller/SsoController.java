package com.quicky.demo.controller;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Scope("prototype")
public class SsoController {

	@Autowired
	private StringRedisTemplate redisTemplate;

	/**
	 * 判断key是否存在
	 * 
	 * @param key
	 * @return
	 */
	@RequestMapping("/redis/hasKey/{key}")
	@ResponseBody
	public Boolean hasKey(@PathVariable(name = "key") String key) {
		return redisTemplate.hasKey(key);
	}

	@RequestMapping("/sso/checkUsernameAndPassword")
	private String checkUsernameAndPassword(String username, String password) {
		String flag = null;// 令牌
		if ("liu".equals(username) && "123456".equals(password)) {
			// 用户名+时间戳（这里只是demo，正常项目的令牌应该要更为复杂）
			flag = username + System.currentTimeMillis();
			// 令牌作为key，存用户id作为value（或者直接存储可暴露的部分用户信息也行）设置过期时间（我这里设置3分钟）
			redisTemplate.opsForValue().set(flag, "1", (long) (20), TimeUnit.SECONDS);
		}
		return flag;
	}

	/**
	 * 跳转登录页面
	 */
	@RequestMapping("/sso/loginPage")
	private ModelAndView loginPage(String url) {
		ModelAndView modelAndView = new ModelAndView("login");
		modelAndView.addObject("url", url);
		return modelAndView;
	}

	/**
	 * 页面登录
	 */
	@RequestMapping(name = "/sso/login",method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	private String login(HttpServletResponse response, String username, String password, String url) {
		String check = checkUsernameAndPassword(username, password);
		if (!StringUtils.isEmpty(check)) {
			try {
				Cookie cookie = new Cookie("accessToken", check);
				cookie.setMaxAge(20);
				// 设置域
//	    cookie.setDomain("huanzi.cn");
				// 设置访问路径
				cookie.setPath("/");
				response.addCookie(cookie);
				// 重定向到原先访问的页面
				response.sendRedirect(url);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		return "登录失败";
	}

}
