package com.quicky.demo.licm.mapper;

import com.quicky.licm.model.UserInfo;

import tk.mybatis.mapper.common.Mapper;

public interface UserInfoMapper extends Mapper<UserInfo>{
	
	UserInfo getUserInfobyuserName(String username);

}
