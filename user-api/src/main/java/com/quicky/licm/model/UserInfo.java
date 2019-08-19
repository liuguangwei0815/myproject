package com.quicky.licm.model;

import java.io.Serializable;

public class UserInfo implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 2769668766113419941L;
	private int id;
    private String username;
    private String idCard;
    private String phone;
    private String password;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
    
    
}