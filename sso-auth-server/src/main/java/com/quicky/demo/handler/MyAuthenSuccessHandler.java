package com.quicky.demo.handler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;

/**
 * 认证成功处理
 * 
 * @author liuwei
 *
 */
@Component("myAuthenSuccessHandler")
@Slf4j
public class MyAuthenSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		RequestCache cache = new HttpSessionRequestCache();
		SavedRequest savedRequest = cache.getRequest(request, response);
		String url =  (savedRequest==null?null:savedRequest.getRedirectUrl());
		log.info("认证成功 ok ， authUrl = ",url);
		// response.sendRedirect(authUrl);
		JsonData jsonData = new JsonData(200, "认证OK", url);
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();

		out.write(JSON.toJSONString(jsonData));
		out.flush();
		out.close();

	}

}
