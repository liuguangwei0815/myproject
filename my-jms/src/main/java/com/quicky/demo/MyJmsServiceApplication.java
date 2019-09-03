package com.quicky.demo;



import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MyJmsServiceApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(MyJmsServiceApplication.class).web(WebApplicationType.SERVLET).run(args);
	}

}
