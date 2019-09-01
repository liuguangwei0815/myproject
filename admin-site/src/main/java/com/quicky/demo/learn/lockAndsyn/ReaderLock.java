package com.quicky.demo.learn.lockAndsyn;

public class ReaderLock extends Thread {
	
	private BufferLock bfs;
	
	
	
	
	public ReaderLock(BufferLock bfs) {
		super();
		this.bfs = bfs;
	}




	@Override
	public void run() {
		try {
			bfs.reader();
		} catch (InterruptedException e) {
			System.out.println("我不讀了");
		}
		
		System.err.println("讀取完畢");
	}

}
