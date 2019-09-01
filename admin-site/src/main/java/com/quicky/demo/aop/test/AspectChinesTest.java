package com.quicky.demo.aop.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AspectChinesTest {
	
	
	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext oo = new AnnotationConfigApplicationContext(AspectChinesAppConfig.class);
		Person  cbean = (Person)oo.getBean("chinese");
		cbean.say("我是");
		
	}

}
