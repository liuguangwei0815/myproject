package com.quicky.demo.learn.lockAndsyn;

import java.util.ArrayList;
import java.util.List;

public class ArrayTest {
	
	public static void main(String[] args) {
//		String[] str = new String[3];
//		String[] str1 = {"a","b","c"};
//		String[] str2 = new String[] {"a","b","c","d"};
//		str2[0] = "g";
//		System.out.println(str2[0]);
		
		List<String> lis = new ArrayList<>();
		lis.add("3");
		lis.set(0, "a");
		lis.forEach(n->{System.out.println(n);});
		
		
		
		
	}

}
