package com.quicky.demo.aop.test;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

/**
 * 切面配置类
 * @author Administrator
 *
 */
@Configuration
@Aspect
public class AspectChinesConfig {
	
	//rvt 标识 被aop类的方法返回值
	@AfterReturning(returning = "rvt" ,pointcut = "execution(* com.quicky.demo.aop.test.*.*(..))")
	public void log(Object rvt) {
		System.out.println("AspectChinesConfig==获取目标方法返回值 :" + rvt);
	}
	
	
	
	
	

}
