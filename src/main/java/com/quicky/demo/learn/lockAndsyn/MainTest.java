package com.quicky.demo.learn.lockAndsyn;

public class MainTest {
	
	public static void main(String[] args) {
		BufferSyn bs = new BufferSyn();
		
		Writer w = new Writer(bs);
		
		Reader rd = new Reader(bs);
		
		w.start();
		
		rd.start();
		
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				long start = System.currentTimeMillis();
				for(;;){
					if(System.currentTimeMillis()-start>5000){
						
						System.err.println("不讀取額 ，打斷");
						rd.interrupt();
						
					}
					
					
				}
				
				
			}
		}).start();
		
		
	}

}
