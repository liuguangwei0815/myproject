package com.quicky.demo;



import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AccoutServiceApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(AccoutServiceApplication.class).web(WebApplicationType.SERVLET).run(args);
	}

}
