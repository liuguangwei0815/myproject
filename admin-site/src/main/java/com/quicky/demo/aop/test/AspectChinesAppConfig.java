package com.quicky.demo.aop.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

/**
 * 配置类
 * @author Administrator
 *
 */
@Configuration
@Import(AspectChinesConfig.class)//导入切面配置类
@EnableAspectJAutoProxy
public class AspectChinesAppConfig {
   
	//定义bean
	@Bean(name = "chinese")
	public Chinese chinesBean() {
		return new Chinese();
	}
	
}
