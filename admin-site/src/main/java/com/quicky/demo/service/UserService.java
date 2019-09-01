package com.quicky.demo.service;

import java.util.List;

import com.quicky.demo.model.User;

public interface UserService  {

    public List<User> findAll();
    
    User selectByName(String name);
}