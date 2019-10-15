package com.quicky.demo.server.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quicky.demo.bean.ProductCategory;
import com.quicky.demo.repository.ProductCategoryRepository;
import com.quicky.demo.server.CategoryService;

/**
 * 类目 Created by 廖师兄 2017-05-09 10:16
 */
@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private ProductCategoryRepository repository;

	@Override
	public ProductCategory findOne(Integer categoryId) {
		/**
		 * 查不到返回null .get 抛异常
		 */
		return repository.findById(categoryId).orElse(null);
	}

	@Override
	public List<ProductCategory> findAll() {
		return repository.findAll();
	}

	@Override
	public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
		return repository.findByCategoryTypeIn(categoryTypeList);
	}

	@Override
	public ProductCategory save(ProductCategory productCategory) {
		return repository.save(productCategory);
	}
}
