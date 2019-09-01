package com.quicky.demo.lockandsyn;

public class Reader extends Thread{

	BufferClass bf;
	
	
	
	
	public Reader(BufferClass bf) {
		super();
		this.bf = bf;
	}





	@Override
	public void run() {
			bf.read();
		
		System.out.println("读结束");   
	}
	
	
}
