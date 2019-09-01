package com.quicky.demo.learn.spring;
/**
 * 发布订阅者启动类
 * @author Administrator
 *
 */

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

public class AppClass {
	
	@Bean(name = "getPushBean", initMethod = "initpush")
	@DependsOn("getCusBean")
	public PushBean getPushBean() {
		return new PushBean();
	}
	
	@Bean(name = "getCusBean", initMethod = "reseive")
	public ConsumerBean getCusBean() {
		return new ConsumerBean();
	}
	
	public static void main(String[] args) {
		new AnnotationConfigApplicationContext(AppClass.class);
	}
	

}
