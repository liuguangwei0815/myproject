package com.quicky.demo.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.quicky.demo.interceptor.HttpLoggingInterceptor;
import com.quicky.demo.restclient.RestClient;
import com.quicky.demo.service.RibbonService;


@Service
public class RibbonServiceImpl implements RibbonService {

	private static final Logger log = LoggerFactory.getLogger(RibbonServiceImpl.class);
	
	@Autowired
	RestTemplate restTemplate;

	@Autowired
	RestClient restClient;
	
	
	public String getUserInfoErro() {
		return "Error occurre熔断错误!";
	}

	@Override
	@HystrixCommand(fallbackMethod = "getUserInfoErro")
	public String getUserInfo() {
		return restTemplate.getForObject("http://user-service/getUser", String.class);
	}

	public String getUserInfoPostErro() {
		return "Error occurre熔断错误!";
	}

	@Override
	@HystrixCommand(fallbackMethod = "getUserInfoPostErro")
	public String getUserInfoPost() {
		try {
	
//		String email = "test@hhui.top";
//		String nick = "一灰灰Blog";
//		MultiValueMap<String, Object> request = new LinkedMultiValueMap<>();
//		request.add("email", email);
//		request.add("nick", nick);
//		return restTemplate.postForObject("http://user-service/post", request, String.class);
		Map<String,String> pare = new HashMap<>();
		pare.put("email","sdfs@qq.com");
		pare.put("nick","liuwei");
		return restClient.postEntiy("post", pare, String.class);
		
		} catch (Exception e) {
			log.error("异常",e);
		}
		return null;
	}

}
