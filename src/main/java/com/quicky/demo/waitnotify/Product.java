package com.quicky.demo.waitnotify;

import java.util.Queue;
import java.util.Random;

public class Product implements Runnable{
	
	private Queue<String> qu;

	public Product(Queue<String> qu) {
		super();
		this.qu = qu;
	}
	

	@Override
	public void run() {
		while (true) {
			synchronized (qu) {
				if(qu.size()==3) {
					System.err.println("已满");
					try {
						qu.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println("通知继续生产"+qu.size());
				qu.add(""+new Random().nextInt(10000));
				qu.notifyAll();
			}
		}		
	}
	

}
