package com.quicky.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quicky.demo.entity.SysPermission;
import com.quicky.demo.entity.SysRole;
import com.quicky.demo.entity.SysRolePermission;
import com.quicky.demo.entity.SysUser;
import com.quicky.demo.entity.SysUserRole;
import com.quicky.demo.mapper.SysPermissionMapper;
import com.quicky.demo.mapper.SysRoleMapper;
import com.quicky.demo.mapper.SysRolePermissionMapper;
import com.quicky.demo.mapper.SysUserMapper;
import com.quicky.demo.mapper.SysUserRoleMapper;
import com.quicky.demo.service.SysUserService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SysUserServiceImpl implements SysUserService {

	@Autowired
	private SysPermissionMapper sysPermissionMapper;
	@Autowired
	private SysRoleMapper sysRoleMapper;
	@Autowired
	private SysRolePermissionMapper sysRolePermissionMapper;
	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;

	@Override
	public SysUser getByUserNamed(String username) {
		log.info("从数据库中查询用户");
		SysUser user = sysUserMapper.getByUserNamed(username);
		if (null == user)
			return user;
		// 获取角色列表
		List<SysUserRole> userRoleList = sysUserRoleMapper.getRoleList(user.getId());
		List<SysRole> roleList = new ArrayList<SysRole>();
		for (SysUserRole sysUserRole : userRoleList) {
			roleList.add(sysRoleMapper.getByid(sysUserRole.getSysroleid()));
		}
		for (SysRole sysRole : roleList) {
			List<SysPermission> perList = new ArrayList<SysPermission>();
			List<SysRolePermission> rolePerList = sysRolePermissionMapper.getPermissionList(sysRole.getId());
			for (SysRolePermission per : rolePerList) {
				perList.add(sysPermissionMapper.getByid(per.getSyspermissionid()));
			}
			sysRole.setPermisionlist(perList);
		}
		user.setRolelist(roleList);
		return user;
	}

}
