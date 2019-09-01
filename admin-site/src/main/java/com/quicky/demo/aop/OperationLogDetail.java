package com.quicky.demo.aop;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 日志操作注解
 * 
 * @author Administrator
 *
 */
@Documented // 成为api的一部分 他的注释
@Target(value = { ElementType.METHOD }) // 可以定义目标注解的位置
@Retention(value = RetentionPolicy.RUNTIME) // 保留时长
public @interface OperationLogDetail {

	/**
	 * 方法描述，可通过{{tel}} 获取参数
	 * 
	 * @return
	 */
	String detail() default "";

	/**
	 * 日志级别 0 - 9
	 * 
	 * @return
	 */
	int level() default 0;

	/**
	 * 操作类型
	 * 
	 * @return
	 */
	OperationType operationType() default OperationType.UNKNOWN;

	/**
	 * 操作独享
	 * 
	 * @return
	 */
	OperationUnit operationUnit() default OperationUnit.UNKNOWN;

}
