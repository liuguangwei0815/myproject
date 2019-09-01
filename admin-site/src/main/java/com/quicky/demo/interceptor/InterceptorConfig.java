package com.quicky.demo.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport{

	private static final Logger log = LoggerFactory.getLogger(InterceptorConfig.class);
	
	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		//设置拦截器
		registry.addInterceptor(new LogInterceptor()).addPathPatterns("/postget");
		super.addInterceptors(registry);
	}

	@Override
	protected void addViewControllers(ViewControllerRegistry registry) {
		//做默认访问地址
//		registry.addViewController("/").setViewName("index");
//		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
		log.info("进入view intercetper");
		super.addViewControllers(registry);
	}
	
	

	
	
}
