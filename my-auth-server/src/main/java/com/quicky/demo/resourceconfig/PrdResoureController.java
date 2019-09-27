package com.quicky.demo.resourceconfig;

import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 受保护的资源
 * @author Administrator
 *
 */
@RestController
@Scope("prototype")
public class PrdResoureController {
	
	@GetMapping("/haha/sayHello")
	public String hhh(String name) {
		return "haha"+name;
	}
	
	@GetMapping("/haha/sayHi")
	@PreAuthorize("hasAnyRole('userIndex')")
	public String hhh1(String name) {
		return "sayHi"+name;
	}
	
	
	@GetMapping("/haha1/sayHi")
	@PreAuthorize("hasAnyRole('userIndex')")
	public String hhh12(String name) {
		return "haha1..sayHi"+name;
	}

	/**
	 * hasAnyRole 角色
	 *  hasAnyAuthority 这是控制权限
	 * @param name
	 * @return
	 */
	@GetMapping("/haha/sayHi3")
	@PreAuthorize("hasAnyAuthority('userIndex')")
	public String hhh3(String name) {
		return "sayHi3"+name;
	}
}
