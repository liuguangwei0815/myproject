package com.quicky.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quicky.demo.bean.ProductCategory;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer>{
	
	List<ProductCategory> findByCategoryTypeIn(List<Integer> list);

}
