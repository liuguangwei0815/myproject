package com.quicky.demo.mapper;

import java.util.List;

import com.quicky.demo.entity.SysUserRole;

import tk.mybatis.mapper.common.Mapper;

public interface SysUserRoleMapper extends Mapper<SysUserRole>{
	
	List<SysUserRole> getList(SysUserRole obj);

}
