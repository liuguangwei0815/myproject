package com.quicky.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quicky.demo.licm.mapper.UserInfoMapper;
import com.quicky.demo.mapper.UserMapper;
import com.quicky.demo.model.User;
import com.quicky.demo.service.UserService;
import com.quicky.licm.model.UserInfo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private UserInfoMapper userInfoMapper; 
	
	
	@Override
	public User getUserbyuserName(String username) {
		return userMapper.getByUserNamed(username);
	}

	@Override
	public UserInfo getUserInfobyuserName(String username) {
		return userInfoMapper.getUserInfobyuserName(username);
	}

	@Override
	@Transactional("baseTransationManager")
	public boolean createUser() {
		User user = new User();
		user.setId(33);
		user.setIdCard("idcc");
		user.setPhone("123123");
		user.setUsername("mick");
		userMapper.insert(user);
		return true;
	}
	
	

}
