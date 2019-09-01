package com.quicky.demo.learn.spring;

public class ConsumerBean {

	public void reseive() {
		PushMange.getInstance().add(s->{System.out.println("resaves"+s);});
	}
	
}
