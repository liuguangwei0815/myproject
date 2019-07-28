package com.quicky.demo.lockandsyn;

public class ReaderLock extends Thread{

	BufferClass bf;
	
	
	
	
	public ReaderLock(BufferClass bf) {
		super();
		this.bf = bf;
	}





	@Override
	public void run() {
		bf.read();
		System.out.println("读结束");   
	}
	
	
}
