package com.quicky.demo.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Proxy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@DynamicUpdate
@Proxy(lazy = false)
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategory implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2356624754591391551L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@GeneratedValue
	private Integer categoryId; // 
	
	private String categoryName; // 类目名字
	
	private Integer categoryType; // 类目编号
	
	private Date createTime; // 创建时间
	
	private Date updateTime; // 修改时间
	

}
