package com.quicky.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.quicky.demo.model.User;
import com.quicky.demo.service.UserService;

@RestController
public class IndexController {

    @RequestMapping("index")
    public String index() {
        return "hello world!";
    }
    
    @Autowired
    private UserService userService;

    /**
     * test redis case
     * @return
     */
    @GetMapping("/getUsers")
    @ResponseBody
    public List<User> getUsers(){
        return userService.findAll();
    }
}