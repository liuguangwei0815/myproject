package com.quicky.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.quicky.demo.mapper.UserMapper;
import com.quicky.demo.model.User;
import com.quicky.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Cacheable(value = "findAll333", key = "'test'")
    public List<User> findAll() {
        System.out.println("如果没打印这句话，说明走了缓存！");
        List<User> list = new ArrayList<User>();
        User u = new User();
        u.setId(1);
        u.setUsername("1112888");
        list.add(u);
        return list;
    }

	@Override
	public User selectByName(String name) {
		try {
			return userMapper.selectByName(name);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
}