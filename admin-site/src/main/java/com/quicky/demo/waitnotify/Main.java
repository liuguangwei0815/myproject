package com.quicky.demo.waitnotify;

import java.util.LinkedList;
import java.util.Queue;

public class Main {

	public static void main(String[] args) {
		Queue<String> qu = new LinkedList<String>();
		Product p = new Product(qu);
		Cusoter c = new Cusoter(qu);
		Thread pt = new Thread(p);
		Thread ct = new Thread(c);
		pt.start();
		ct.start();
	}
}
