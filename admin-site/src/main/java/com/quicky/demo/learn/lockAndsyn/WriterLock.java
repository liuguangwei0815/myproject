package com.quicky.demo.learn.lockAndsyn;

public class WriterLock extends Thread {

private BufferLock bfs;
	
	
	
	
	public WriterLock(BufferLock bfs) {
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
