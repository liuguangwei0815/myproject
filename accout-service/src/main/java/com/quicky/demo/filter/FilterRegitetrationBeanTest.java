package com.quicky.demo.filter;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * 测试spring boot Filter
 * @author Administrator
 *
 */
@Configuration
public class FilterRegitetrationBeanTest {
	
	
	
	@Bean
	@Order(Integer.MAX_VALUE-1)
	public FilterRegistrationBean<FilterDemo1> getDem1() {
		FilterRegistrationBean<FilterDemo1> bean = new FilterRegistrationBean<FilterDemo1>();
		bean.setFilter(new FilterDemo1());
		bean.addUrlPatterns("/getUser");
		Map<String,String> par = new HashMap<>();
		par.put("exclusions", "*.js,*.html,*.gif,*.jpg,*.png,*.css,*.ico,*.jsp,/druid/*,/download/*");
		bean.setInitParameters(par);
		return bean;
	}
	@Bean
	@Order(Integer.MAX_VALUE)
	public FilterRegistrationBean<FilterDemo2> getDem2() {
		FilterRegistrationBean<FilterDemo2> bean = new FilterRegistrationBean<FilterDemo2>();
		bean.setFilter(new FilterDemo2());
		bean.addUrlPatterns("/getUserInfo");
		Map<String,String> par = new HashMap<>();
		par.put("exclusions", "my filter 2");
		bean.setInitParameters(par);
		return bean;
	}

}
