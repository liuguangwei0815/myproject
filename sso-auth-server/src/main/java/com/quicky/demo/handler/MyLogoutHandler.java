package com.quicky.demo.handler;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

@Component
public class MyLogoutHandler implements LogoutHandler {

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		try {
			String aa = request.getParameter("aa");// aa即为前端传来自定义跳转url地址
			response.sendRedirect(aa);// 实现自定义重定向
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}