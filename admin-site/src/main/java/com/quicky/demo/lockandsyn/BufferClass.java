package com.quicky.demo.lockandsyn;

public class BufferClass {
	
	private Object obj;
	
	
	public BufferClass() {
		this.obj = this;
	}


	public void write() {
		
		synchronized (obj) {
			//假设需要写很久时间
			long startTime = System.currentTimeMillis();
			System.out.println("开始写了");
			for (;;) {
				if((System.currentTimeMillis()-startTime)>Integer.MAX_VALUE)	
					break;
			}
			System.out.println("终于写完了");
		}
	}
	
	public void read() {
		synchronized (obj) {
			System.out.println("read.....");
		}
	}
	

}
