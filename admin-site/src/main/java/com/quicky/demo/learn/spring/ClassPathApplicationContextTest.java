package com.quicky.demo.learn.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ClassPathApplicationContextTest {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		ClassPathXmlApplicationContext con = new ClassPathXmlApplicationContext();
		BeanTest  class1 = (BeanTest)Class.forName("com.quicky.demo.learn.spring.BeanTest").newInstance();
		class1.say();
		
	}
	
}
