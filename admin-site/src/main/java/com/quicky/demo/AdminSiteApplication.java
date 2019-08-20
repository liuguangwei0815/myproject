package com.quicky.demo;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableCaching
@EnableCircuitBreaker
@EnableDiscoveryClient
public class AdminSiteApplication {

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {

		// 设置超时时间
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setConnectTimeout(30000); // 5分钟 连接超时
		requestFactory.setReadTimeout(30000); // 读取超时
		requestFactory.setConnectionRequestTimeout(30000); // 请求连接超时
		// 设置日志拦截
//		ClientHttpRequestInterceptor ri = new HttpLoggingInterceptor();
//		List<ClientHttpRequestInterceptor> ris = new ArrayList<>();
//		ris.add(ri);
		RestTemplate template = new RestTemplate(requestFactory);
//		template.setInterceptors(ris);
		return template;
	}

	public static void main(String[] args) {
		new SpringApplicationBuilder(AdminSiteApplication.class).web(WebApplicationType.SERVLET).run(args);
	}

}
