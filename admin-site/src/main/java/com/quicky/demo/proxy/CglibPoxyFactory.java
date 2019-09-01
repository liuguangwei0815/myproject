package com.quicky.demo.proxy;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

public class CglibPoxyFactory implements MethodInterceptor{
	
	
	private Object obj;
	
	

	public CglibPoxyFactory(Object obj) {
		super();
		this.obj = obj;
	}

	@Override
	public Object intercept(Object arg0, Method arg1, Object[] arg2, MethodProxy arg3) throws Throwable {
		System.out.println("before...");
		Object ret = arg1.invoke(obj, arg2);
		System.out.println("after...");
		return ret;
	}
	
	public Object getObejct() {
		Enhancer cer = new Enhancer();
		cer.setSuperclass(obj.getClass());
		cer.setCallback(this);
		return cer.create();
		
	}
	

	
	
}
