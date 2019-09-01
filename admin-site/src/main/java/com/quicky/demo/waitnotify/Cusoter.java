package com.quicky.demo.waitnotify;

import java.util.Queue;

public class Cusoter implements Runnable{
	private Queue<String> qu;

	public Cusoter(Queue<String> qu) {
		super();
		this.qu = qu;
	}
	@Override
	public void run() {
		while (true) {
			synchronized (qu) {
				if(qu.isEmpty()) {
					System.err.println("已销售完");
					try {
						qu.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				String c = qu.remove();
				System.out.println("通知继续消费"+c);
				qu.notifyAll();
			}
		}		
	}

}
