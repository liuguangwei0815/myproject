package com.quicky.demo.iplimit;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解模式
 * 
 * @author Administrator
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IpLimiter {

	/**
	 * 单位时间限制通过请求数 统一秒访问5次
	 */
	long limit() default 5;
	/**
	 * 显示时间 默认10s
	 */
	long timet() default 10;
	/**
	 * 提示语
	 */
	String messge();

}
