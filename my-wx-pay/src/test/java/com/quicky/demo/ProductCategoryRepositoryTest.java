package com.quicky.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.quicky.demo.bean.ProductCategory;
import com.quicky.demo.repository.ProductCategoryRepository;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductCategoryRepositoryTest {

	@Autowired
	private ProductCategoryRepository productCategoryRepository;
	
	@Test
	public void test() {
		ProductCategory pc = productCategoryRepository.getOne(1l);
		log.info("===>pc:{}",pc.toString());
	}
	
	@Test
	public void test1() {
		ProductCategory pc = new ProductCategory();
		pc.setCategoryName("男生最爱");
		pc.setCategoryType(2l);
		productCategoryRepository.save(pc);
	}
	
	
	
}
