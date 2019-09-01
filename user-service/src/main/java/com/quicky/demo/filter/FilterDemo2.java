package com.quicky.demo.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class FilterDemo2 implements Filter{

	
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("2222");
		System.out.println(this.getClass().getName()+"init");
	}

	@Override
	public void destroy() {
		System.out.println(this.getClass().getName()+"destroy");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println(this.getClass().getName()+"FilterDemo2 doFilter");
		System.out.println(request.getAttribute("paramName"));
		chain.doFilter(request, response);
	}

}
