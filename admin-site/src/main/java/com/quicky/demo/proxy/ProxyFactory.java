package com.quicky.demo.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理
 * 
 * @author Administrator
 *
 */
public class ProxyFactory {

	private Object obj;

	public ProxyFactory(Object obj) {
		super();
		this.obj = obj;
	}

	public Object getObject() {

		Object xy = Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(),
				new InvocationHandler() {
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						System.out.println("do before");
						Object result = method.invoke(obj, args);
						System.out.println("do after");
						return result;
					}
					
				});
		return xy;

	}

}
