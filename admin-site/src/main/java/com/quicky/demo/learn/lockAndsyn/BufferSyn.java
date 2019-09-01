package com.quicky.demo.learn.lockAndsyn;

public class BufferSyn {
	
	private final Object obj;

	public BufferSyn() {
		this.obj = this;
	}
	
	
	public void write(){
		
		synchronized (obj) {
			
			long start = System.currentTimeMillis();
			
			for(;;){
				
				if(System.currentTimeMillis()-start>Integer.MAX_VALUE){
					break;
				}
			}
			System.out.println("写完了");
			
		}
		
	}
	
	
	public void reader(){
		
		synchronized (obj) {
			System.err.println("我正读取");
		}
		
	}
	
	
	
	
	
	

}
