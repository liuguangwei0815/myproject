package com.quicky.demo.security;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MyUserDetails implements UserDetailsService{

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<GrantedAuthority> list = new ArrayList<>();
		String password = "";
		if(StringUtils.equals(username, "admin")) {
			list.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			password = passwordEncoder.encode("123456");
		}
		if(StringUtils.equals(username, "liuwei")) {
			list.add(new SimpleGrantedAuthority("ROLE_USER"));
			password = passwordEncoder.encode("123456");
		}
		log.info("password:{}",password);	
		return new User(username, password, list);
	}

}
