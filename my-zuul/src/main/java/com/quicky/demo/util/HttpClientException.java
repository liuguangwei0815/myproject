package com.quicky.demo.util;

import org.springframework.core.NestedRuntimeException;

/**
 *  @ClassName HttpClientException
 *  @Description 系统交互异常
 *  @author liuwenbin
 *  @version 1.0
 *  @date 2017年6月3日
 */
public class HttpClientException extends NestedRuntimeException {

	private static final long serialVersionUID = -6331376879800219105L;

	/**
	 * 
	 * @param message
	 * @param cause
	 */
	public HttpClientException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * 
	 * @param message
	 */
	public HttpClientException(String message) {
		super(message);
	}

}
