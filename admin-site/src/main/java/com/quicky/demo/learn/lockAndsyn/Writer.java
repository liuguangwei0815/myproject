package com.quicky.demo.learn.lockAndsyn;

public class Writer extends Thread {

private BufferSyn bfs;
	
	
	
	
	public Writer(BufferSyn bfs) {
		super();
		this.bfs = bfs;
	}




	@Override
	public void run() {
		System.err.println("write.....");
		bfs.write();
		System.err.println("write.....end");
	}
	
	
	
}
