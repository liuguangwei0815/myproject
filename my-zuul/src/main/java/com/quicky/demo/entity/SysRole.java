package com.quicky.demo.entity;

import java.io.Serializable;
import java.util.List;

import lombok.ToString;

/**
 * 系统角色
 * 
 * @author Administrator
 *
 */
/**
 * @param permisionlist
 */
@ToString
public class SysRole implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4007896593508184517L;
	private Long id;
	private String rolecode;
	private String rolename;
	private List<SysPermission> permisionlist;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRolecode() {
		return rolecode;
	}

	public void setRolecode(String rolecode) {
		this.rolecode = rolecode;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public List<SysPermission> getPermisionlist() {
		return permisionlist;
	}

	public void setPermisionlist(List<SysPermission> permisionlist) {
		this.permisionlist = permisionlist;
	}

}
