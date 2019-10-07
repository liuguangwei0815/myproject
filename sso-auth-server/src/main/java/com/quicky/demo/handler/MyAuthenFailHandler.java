package com.quicky.demo.handler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;

/**
 * 认证失败处理
 * 
 * @author liuwei
 *
 */
@Component("myAuthenFailHandler")
@Slf4j
public class MyAuthenFailHandler extends SimpleUrlAuthenticationFailureHandler {
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		log.info("认证失败");
		JsonData jsonData = new JsonData(401, "error", null);
		PrintWriter witer = response.getWriter();
		witer.write(JSON.toJSONString(jsonData));
		witer.flush();
		witer.close();
	}

}
