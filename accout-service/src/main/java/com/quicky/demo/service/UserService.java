package com.quicky.demo.service;

import com.quicky.demo.model.User;
import com.quicky.licm.model.UserInfo;

public interface UserService {
	User getUserbyuserName(String username);
	
	UserInfo getUserInfobyuserName(String username);
	
	
	boolean createUser();

	
}
