package com.quicky.demo.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.quicky.demo.entity.SysPermission;
import com.quicky.demo.entity.SysRole;
import com.quicky.demo.entity.SysUser;
import com.quicky.demo.service.SysUserService;

@Component
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private SysUserService sysUserService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SysUser sysUser = sysUserService.getByUserNamed(username);
		if (null == sysUser)
			throw new UsernameNotFoundException(username);
		List<SimpleGrantedAuthority> ahthority = new ArrayList<SimpleGrantedAuthority>();
		for (SysRole sysRole : sysUser.getRolelist()) {
			for (SysPermission spn : sysRole.getPermisionlist()) {
				ahthority.add(new SimpleGrantedAuthority(spn.getCode()));
			}
		}
		return new User(sysUser.getUsername(), sysUser.getPassword(), ahthority);
	}

}
