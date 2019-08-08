package com.quicky.demo.learn.lockAndsyn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import com.github.pagehelper.util.StringUtil;

public class LambdaThread {
	// https://www.cnblogs.com/coprince/p/8692972.html 学习地址
	public static void main(String[] args) {
		//匿名， 比如排序，collecto
//		new Thread(()->  {
//			System.out.println("1");
//			System.out.println("2");
//		} ) .start();
		//for
		//List<String> features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
		//features.forEach(n->{System.out.println(n);});
		//features.forEach(System.out::equals);
		
		List<String> features1 = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
		//predicate 谓语
//		Predicate<String> prd = (str1)->str1.length() > 6 || str1.length() <= 7;
//		for (String str : features1) {
//			if(prd.test(str))
//				System.out.println(str);
//		}
		//多条件
		//features1.stream().filter((str1)->str1.length() > 6 || str1.length() <= 7).forEach(n->System.out.println(n));
		//features1.stream().filter((str1)->str1.length() > 6 || str1.length() <= 7).forEach(System.out::println);
//		Predicate<String> c1 = (n) -> n.startsWith("L");
//		Predicate<String> c2 = (n) -> n.startsWith("D");
//		Predicate<String> c3 = c1.or(c2); 
//		features1.stream().filter(c3).forEach((n)->{System.out.println(n);});
		//map reduce(折叠函数)
		List<Integer> costBeforeTax = Arrays.asList(100, 100, 100, 100, 100);
		Integer ss = costBeforeTax.stream().map((price1)->price1+100).reduce((ssum,price1)->ssum+price1).get();
		System.out.println(ss);
		
		List<testM> testList = new ArrayList<>();
		testM m = new testM();
		m.setAge("1");
		m.setName("a");
		testM m1 = new testM();
		m1.setAge("2");
		m1.setName("b");
		testList.add(m);
		testList.add(m1);
		//谓语
		Predicate<testM> rm = (testM)->testM.getName().equals("a");
		testList.stream().filter(rm).forEach((n)->{n.setAge("3");});
		testList.stream().forEach((n)->{
			System.out.println(n.getAge()+"___"+n.getName());
		});
		
		
	}
	
	static class testM {
		private String name;
		private String age;
		
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getAge() {
			return age;
		}

		public void setAge(String age) {
			this.age = age;
		}

	}

}
