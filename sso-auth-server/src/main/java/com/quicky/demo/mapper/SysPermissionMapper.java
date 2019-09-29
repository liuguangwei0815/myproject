package com.quicky.demo.mapper;

import com.quicky.demo.entity.SysPermission;

import tk.mybatis.mapper.common.Mapper;

public interface SysPermissionMapper extends Mapper<SysPermission>{
	
	SysPermission getOne(SysPermission param);

}
