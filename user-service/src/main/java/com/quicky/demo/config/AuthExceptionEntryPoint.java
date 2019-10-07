//package com.quicky.demo.config;
//
//import java.io.IOException;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.stereotype.Component;
//
//import lombok.extern.slf4j.Slf4j;
//
//
///**
// * 401异常认证捕获
// * @author liuwei
// *
// */
//@Component
//@Slf4j
//public class AuthExceptionEntryPoint implements AuthenticationEntryPoint{
//	
//	@Value("server.url")
//	private String serverUrl;
//
//	@Override
//	public void commence(HttpServletRequest request, HttpServletResponse response,
//			AuthenticationException authException) throws IOException, ServletException {
//		log.info("进入到entry poit 方法{}");
//		response.sendRedirect(serverUrl+ "/error/401");
//	}
//
//}
