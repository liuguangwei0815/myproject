package com.quicky.demo.restclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.quicky.demo.util.EnDeCryptionUtil;

/**
 * 封装restTemplate restTemplate
 * 
 * @author Administrator
 *
 */
@Component
public class RestClient {

	// 请求地址
	private final static String REQ_URL = "http://user-service/";

	@Autowired
	private RestTemplate restTemplate;

	@SuppressWarnings("unchecked")
	public <T> T postEntiy(String url, Object obj, Class<T> clazz) {
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("jsonParam", EnDeCryptionUtil.enCryption(obj));
		String reponse = restTemplate.postForObject(REQ_URL+url, map, String.class);
		if(clazz==String.class) {
			return (T) reponse;
		}
		return JSON.parseObject(reponse,clazz);
	}

}
