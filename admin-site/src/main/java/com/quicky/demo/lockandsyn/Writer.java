package com.quicky.demo.lockandsyn;

public class Writer extends Thread{

	BufferClass bf;
	
	
	
	
	
	public Writer(BufferClass bf) {
		super();
		this.bf = bf;
	}





	@Override
	public void run() {
		bf.write();
	}
	
	
}
