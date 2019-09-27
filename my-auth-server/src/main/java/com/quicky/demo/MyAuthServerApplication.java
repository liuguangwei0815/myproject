package com.quicky.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MyAuthServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyAuthServerApplication.class, args);
    }
}