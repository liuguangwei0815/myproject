package com.quicky.demo.entity;

import java.io.Serializable;
import java.util.List;

import lombok.ToString;

/**
 * 系统用户
 * 
 * @author Administrator
 *
 */
@ToString
public class SysUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String usercode;
	private String username;
	private String password;
	private String salt;
	private List<SysRole> rolelist;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsercode() {
		return usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public List<SysRole> getRolelist() {
		return rolelist;
	}

	public void setRolelist(List<SysRole> rolelist) {
		this.rolelist = rolelist;
	}

}
