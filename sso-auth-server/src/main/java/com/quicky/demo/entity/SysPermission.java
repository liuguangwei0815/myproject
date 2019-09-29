package com.quicky.demo.entity;

import java.io.Serializable;

/**
 * 系统权限
 * 
 * @author Administrator
 *
 */
public class SysPermission implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6232917311648199415L;
	private Long id;
	private String code;
	private String name;
	private Integer type;// 资源类型
	private String url;// 路径
	private Integer sort;// 排序
	private Long pid;// 父

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

}
