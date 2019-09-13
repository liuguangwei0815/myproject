package com.quicky.demo.mapper;

import org.apache.ibatis.annotations.Param;

import com.quicky.demo.entity.SysUser;

import tk.mybatis.mapper.common.Mapper;

public interface SysPermissionVoMapper extends Mapper<SysUser>{
	
	SysUser getByUserNamed(@Param("username")String username);

}
