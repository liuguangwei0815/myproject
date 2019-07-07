package com.quicky.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class User {
    private int id;
    private String username;
    private String idCard;
    private String phone;
    private String password;
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(int id, String username, String idCard, String phone, String password) {
		super();
		this.id = id;
		this.username = username;
		this.idCard = idCard;
		this.phone = phone;
		this.password = password;
	}
    
    
}