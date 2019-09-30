package com.quicky.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;

import java.security.Principal;

@RestController
@Slf4j
public class UserController {

    @RequestMapping("/user/me")
    public Principal user(Principal principal) {
    	log.info("Principal:{}",JSON.toJSON(principal));
        System.out.println(principal);
        return principal;
    }

}
