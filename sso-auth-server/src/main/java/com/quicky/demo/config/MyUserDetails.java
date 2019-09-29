package com.quicky.demo.config;

import java.util.ArrayList;
import java.util.List;

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
		list.add(new SimpleGrantedAuthority("userIndex"));
		String password = passwordEncoder.encode("123456");
		log.info("password:{}",password);
		return new User("liuwei", password, list);
	}

}
