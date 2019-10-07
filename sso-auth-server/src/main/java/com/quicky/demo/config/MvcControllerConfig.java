package com.quicky.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置MvcController
 * @author liuwei
 *
 */
@Configuration
public class MvcControllerConfig implements WebMvcConfigurer{

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login_page").setViewName("login");
		registry.addViewController("/index").setViewName("index");
	}

}
