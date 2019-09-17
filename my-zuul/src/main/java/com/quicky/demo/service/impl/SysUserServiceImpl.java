package com.quicky.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

@Service
public class SysUserServiceImpl implements SysUserService {
	Logger log = LoggerFactory.getLogger(this.getClass());
	
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
		SysUser	user = new SysUser();
		user.setUsername(username);
		user = sysUserMapper.getOne(user);
		if (null == user)
			return user;
		// 获取角色列表
		SysUserRole surparam = new SysUserRole();
		surparam.setSysuserid(user.getId());
		List<SysUserRole> userRoleList = sysUserRoleMapper.getList(surparam);
		List<SysRole> roleList = new ArrayList<SysRole>();
		for (SysUserRole sysUserRole : userRoleList) {
			SysRole sysRolePar= new SysRole();
			sysRolePar.setId(sysUserRole.getSysroleid());
			roleList.add(sysRoleMapper.getOne(sysRolePar));
		}
		for (SysRole sysRole : roleList) {
			List<SysPermission> perList = new ArrayList<SysPermission>();
			SysRolePermission srpPar = new SysRolePermission();
			srpPar.setSysroleid(sysRole.getId());
			List<SysRolePermission> rolePerList = sysRolePermissionMapper.getList(srpPar);
			for (SysRolePermission per : rolePerList) {
				SysPermission sppar = new SysPermission();
				sppar.setId(per.getSyspermissionid());
				perList.add(sysPermissionMapper.getOne(sppar));
			}
			sysRole.setPermisionlist(perList);
		}
		user.setRolelist(roleList);
		return user;
	}

}
