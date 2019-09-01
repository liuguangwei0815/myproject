package com.quicky.demo.restservice;

import org.springframework.web.client.RestTemplate;

/**
 * restTemplate单例
 * 
 * @author Administrator
 * @deprecated
 */
public enum RestEnum {

	/**
	 * restTemplate单例
	 */
	SINGLE_INSTANCE;

	private RestTemplate restTemplate;

	RestEnum() {
//		restTemplate = new RestTemplateBuilder().setConnectTimeout(Duration.ofMillis(5000))
//				.setReadTimeout(Duration.ofMillis(5000)).build();
//		restTemplate.setInterceptors(ris);
//		restTemplate.setRequestFactory(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
	}

}
