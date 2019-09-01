package com.quicky.demo.lockandsyn;

import java.util.concurrent.locks.ReentrantLock;

public class BufferClassLock {

	private static final ReentrantLock lock = new ReentrantLock();

	public void write() {
		try {
			lock.lock();
			// 假设需要写很久时间
			long startTime = System.currentTimeMillis();
			System.out.println("开始写了");
			for (;;) {
				if ((System.currentTimeMillis() - startTime) > Integer.MAX_VALUE)
					break;
			}
			System.out.println("终于写完了");

		} finally {
			lock.unlock();
		}
	}

	public void read() {
		try {
			lock.lockInterruptibly();
			System.out.println("read.....");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}

}
