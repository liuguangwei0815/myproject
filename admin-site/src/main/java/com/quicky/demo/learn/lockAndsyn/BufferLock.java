package com.quicky.demo.learn.lockAndsyn;

import java.util.concurrent.locks.ReentrantLock;

public class BufferLock {

	private   ReentrantLock lock = new ReentrantLock();

	public void write() {
		lock.lock();
		try {
			synchronized (lock) {
				
			}

			long start = System.currentTimeMillis();

			for (;;) {

				if (System.currentTimeMillis() - start > Integer.MAX_VALUE) {
					break;
				}
			}
			System.out.println("写完了");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}

	}

	public void reader() throws InterruptedException {
		lock.lockInterruptibly();
		try {
			System.err.println("開始讀取");
		}finally {
			lock.unlock();
		}
		
			

	}

}
