//package com.quicky.demo.config;
//
//import java.io.IOException;
//
//import javax.security.sasl.AuthenticationException;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
//import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
//import org.springframework.security.web.savedrequest.RequestCache;
//
//@Configuration
//class MyOAuth2ClientAuthenticationProcessingFilter extends OAuth2ClientAuthenticationProcessingFilter {
//
//	private RequestCache requestCache = new HttpSessionRequestCache();
//
//	public MyOAuth2ClientAuthenticationProcessingFilter(String defaultFilterProcessesUrl) {
//		super(defaultFilterProcessesUrl);
//	}
//
//	@Override
//	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
//			throws AuthenticationException, IOException, ServletException {
//		requestCache.saveRequest(request, response);
//		return super.attemptAuthentication(request, response);
//	}
//}