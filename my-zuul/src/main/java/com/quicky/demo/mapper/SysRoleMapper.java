package com.quicky.demo.mapper;

import org.apache.ibatis.annotations.Param;

import com.quicky.demo.entity.SysRole;

import tk.mybatis.mapper.common.Mapper;

public interface SysRoleMapper extends Mapper<SysRole>{
	
	SysRole getByid(@Param("id")Long id);

}
