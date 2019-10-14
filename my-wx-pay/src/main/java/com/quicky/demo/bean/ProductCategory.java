package com.quicky.demo.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Proxy;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
@DynamicUpdate
@Proxy(lazy = false)
@ToString
public class ProductCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@GeneratedValue
	private Long categoryId; // 
	
	private String categoryName; // 类目名字
	
	private Long categoryType; // 类目编号
	
	private Date createTime; // 创建时间
	
	private Date updateTime; // 修改时间
	
    public ProductCategory() {
    }

    public ProductCategory(String categoryName, Long categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }

}
