package com.quicky.demo.proxy;

public class ProxMainTest {

	public static void main(String[] args) {
		// jdk proxy
//		ProxyInter in = new PrxyInterImp();
//		ProxyFactory fa = new ProxyFactory(in);
//		ProxyInter obb = (ProxyInter)fa.getObject();
//		obb.buybook();
		//cglib proxy
		PrxyInterImp im = new PrxyInterImp();
		CglibPoxyFactory cbfa = new CglibPoxyFactory(im);
		PrxyInterImp xy = (PrxyInterImp)cbfa.getObejct();
		xy.buybook();
		
		
		
	}

}
