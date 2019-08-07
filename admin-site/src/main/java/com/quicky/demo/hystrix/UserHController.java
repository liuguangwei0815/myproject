package com.quicky.demo.hystrix;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@RestController
@RequestMapping("consumerH")
@DefaultProperties(defaultFallback = "queryUserByIdFallback")
public class UserHController {

    @GetMapping("/{id}")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000"),  //响应超时时间
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),   
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60")
    })
    public String queryUserById(@PathVariable("id") Long id){
        if(id % 2 ==0){
            throw new RuntimeException("");
        }
        return "{\"id\":\"111\",\"name\":\"nanss\"}";
    }

    public String queryUserByIdFallback(){
        return "用户信息查询出现异常！";
    }
}