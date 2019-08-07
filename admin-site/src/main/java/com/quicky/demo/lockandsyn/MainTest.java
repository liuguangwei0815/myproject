package com.quicky.demo.lockandsyn;

public class MainTest {

	
	public static void main(String[] args) {
		BufferClass bfc = new BufferClass();
		Writer wr = new  Writer(bfc);
		Reader rd = new Reader(bfc);
		wr.start();
		rd.start();
		
		
		new Thread(new Runnable() {
			
			
			@Override
			public void run() {
				
				long start = System.currentTimeMillis();

				for(;;) {
					
					if(System.currentTimeMillis()-start>5000) {
						System.out.println("不等了");
						rd.interrupt();
						break;
					}
					
				}
				
			}
		}).start();
		
		
		
	}
	
}
