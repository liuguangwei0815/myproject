package com.quicky.demo.service;

import com.quicky.demo.entity.SysUser;

public interface SysUserService {
	/**
	 * 通过名字查询
	 * @param username
	 * @return
	 */
	SysUser getByUserNamed(String username);
}
