package com.quicky.demo;


import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MyEurekaApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(MyEurekaApplication.class).web(WebApplicationType.SERVLET).run(args);
	}

}
