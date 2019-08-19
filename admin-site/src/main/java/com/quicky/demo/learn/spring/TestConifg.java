package com.quicky.demo.learn.spring;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConifg {

	
	@Value("${spring.datasource.base.url}")
	private String url;
	
	@Bean(name = "getTTTTTbaen")
	public TestConfigBean getTbean() {
		TestConfigBean bean = new TestConfigBean();
		bean.setAge("1");
		bean.setUserName(url);
		return bean;
	}
	
	public static void main(String[] args) {
		List<Consumer<String>>  dd = new ArrayList<>();
		List<String> str = new ArrayList<>();
		str.add("a");
		str.add("不a");
		str.forEach(l->{
		});
		
	}
	
	
}
