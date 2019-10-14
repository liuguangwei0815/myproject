package com.quicky.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quicky.demo.bean.ProductCategory;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long>{

}
