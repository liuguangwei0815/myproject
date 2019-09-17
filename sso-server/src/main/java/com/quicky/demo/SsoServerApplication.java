package com.quicky.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SsoServerApplication {
	public static void main(String[] args) {
		System.out.println("test");
		SpringApplication.run(SsoServerApplication.class, args);
	}
}
