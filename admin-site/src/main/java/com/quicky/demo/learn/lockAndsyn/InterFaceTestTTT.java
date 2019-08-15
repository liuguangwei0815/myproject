package com.quicky.demo.learn.lockAndsyn;

public class InterFaceTestTTT implements InterFaceTest.EM{

	@Override
	public void bbb() {
		System.out.println("aaa");
	}
	
	public static void main(String[] args) {
		InterFaceTest.EM dd = new InterFaceTestTTT();
		
		dd.bbb();
		
	}
	
	
	
}
