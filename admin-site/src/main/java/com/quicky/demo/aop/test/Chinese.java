package com.quicky.demo.aop.test;

public class Chinese implements Person {

	@Override
	public String say(String world) {
		return " AOP"+world;
	}

}
