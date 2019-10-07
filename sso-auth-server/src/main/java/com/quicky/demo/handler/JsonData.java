package com.quicky.demo.handler;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JsonData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 779209597031074137L;
	private Integer code;
	private String msg;
	private String targetUrl;
	
}
