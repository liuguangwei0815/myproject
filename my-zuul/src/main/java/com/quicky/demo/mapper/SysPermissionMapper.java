package com.quicky.demo.mapper;

import org.apache.ibatis.annotations.Param;

import com.quicky.demo.entity.SysPermission;

import tk.mybatis.mapper.common.Mapper;

public interface SysPermissionMapper extends Mapper<SysPermission>{
	
	SysPermission getByid(@Param("id")Long id);

}
