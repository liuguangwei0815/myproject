package com.quicky.demo.learn.spring;

import java.awt.BufferCapabilities.FlipContents;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 测试spring boot Filter
 * @author Administrator
 *
 */
@Configuration
public class FilterRegitetrationBeanTest {
	
	
	
	@Bean
	public FilterRegistrationBean getDem1() {
		FilterRegistrationBean bean = new FilterRegistrationBean();
		bean.setFilter(new FilterDemo1());
		bean.addUrlPatterns("/*");
		Map<String,String> par = new HashMap<>();
		par.put("exclusions", "*.js,*.html,*.gif,*.jpg,*.png,*.css,*.ico,*.jsp,/druid/*,/download/*");
		bean.setInitParameters(par);
		return bean;
	}
	
	
	
	public static void main(String[] args) {
		new AnnotationConfigApplicationContext(FilterRegitetrationBeanTest.class);
	}
	
	

}
