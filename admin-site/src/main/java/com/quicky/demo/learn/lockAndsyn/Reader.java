package com.quicky.demo.learn.lockAndsyn;

public class Reader extends Thread {
	
	private BufferSyn bfs;
	
	
	
	
	public Reader(BufferSyn bfs) {
		super();
		this.bfs = bfs;
	}




	@Override
	public void run() {
		bfs.reader();
		System.err.println("read.....");
	}

}
