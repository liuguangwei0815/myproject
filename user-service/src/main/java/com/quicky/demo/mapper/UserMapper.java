package com.quicky.demo.mapper;

import org.apache.ibatis.annotations.Param;

import com.quicky.demo.model.User;

import tk.mybatis.mapper.common.Mapper;

public interface UserMapper extends Mapper<User>{
	
	User getByUserNamed(@Param("username")String username);

}
