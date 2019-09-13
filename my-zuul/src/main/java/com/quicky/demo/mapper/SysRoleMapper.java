package com.quicky.demo.mapper;

import com.quicky.demo.entity.SysRole;

import tk.mybatis.mapper.common.Mapper;

public interface SysRoleMapper extends Mapper<SysRole>{
	
	SysRole getOne(SysRole obj);

}
