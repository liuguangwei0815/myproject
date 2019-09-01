package com.quicky.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.quicky.demo.model.User;
import com.quicky.demo.util.MyMapper;

@Mapper
public interface UserMapper extends MyMapper<User> {

    @Select("select * from user where username=#{username}")
    User selectByName(String username);
    
    @Update ("update user set phone = #{phone}  where id=#{id}")
    void upById(User user);

}