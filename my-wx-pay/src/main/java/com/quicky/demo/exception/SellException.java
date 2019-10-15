package com.quicky.demo.exception;

import com.quicky.demo.enums.ResultEnum;

import lombok.Getter;

@Getter
public class SellException extends RuntimeException {

	private Integer code;

	public SellException(Integer code, String msg) {
		super(msg);
		this.code = code;
	}

	public SellException(ResultEnum resultEnum) {
		super(resultEnum.getMessage());
		this.code = resultEnum.getCode();
	}

}
