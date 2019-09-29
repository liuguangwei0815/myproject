package com.quicky.demo.mapper;

import com.quicky.demo.entity.SysUser;

import tk.mybatis.mapper.common.Mapper;

public interface SysUserMapper extends Mapper<SysUser>{
	
	SysUser getOne(SysUser obj);

} 