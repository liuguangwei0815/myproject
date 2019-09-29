package com.quicky.demo.entity;

public class SysRolePermission {
	
	private Long id;
	private Long syspermissionid;
	private Long sysroleid;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getSysroleid() {
		return sysroleid;
	}
	public void setSysroleid(Long sysroleid) {
		this.sysroleid = sysroleid;
	}
	public Long getSyspermissionid() {
		return syspermissionid;
	}
	public void setSyspermissionid(Long syspermissionid) {
		this.syspermissionid = syspermissionid;
	}
	

}
