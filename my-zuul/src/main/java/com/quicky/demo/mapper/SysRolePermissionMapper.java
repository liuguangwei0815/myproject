package com.quicky.demo.mapper;

import java.util.List;

import com.quicky.demo.entity.SysRolePermission;

import tk.mybatis.mapper.common.Mapper;

public interface SysRolePermissionMapper extends Mapper<SysRolePermission> {
	List<SysRolePermission> getList(SysRolePermission obj);
}
