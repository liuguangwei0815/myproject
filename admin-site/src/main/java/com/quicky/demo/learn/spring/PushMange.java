package com.quicky.demo.learn.spring;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * 发布订阅
 * 
 * @author Administrator
 *
 */
public class PushMange {

	private static List<Consumer<String>> cu = new ArrayList<>();

	private static class getPushClass {
		static PushMange mage = new PushMange();
	}

	public static PushMange getInstance() {
		return getPushClass.mage;
	}

	public static void publish(final String msg) {
		cu.forEach(l -> {
			l.accept(msg);
		});
	}

	public static void add(Consumer<String> con) {
		cu.add(con);
	}

}
