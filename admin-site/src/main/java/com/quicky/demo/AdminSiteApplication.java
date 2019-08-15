package com.quicky.demo;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableCaching
@EnableCircuitBreaker
@EnableDiscoveryClient
public class AdminSiteApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(AdminSiteApplication.class).web(WebApplicationType.SERVLET).run(args);
	}

}
