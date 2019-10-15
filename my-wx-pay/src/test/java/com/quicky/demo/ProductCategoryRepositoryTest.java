package com.quicky.demo;

import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
		ProductCategory pc = productCategoryRepository.getOne(1);
		log.info("===>pc:{}",pc.toString());
	}
	
	@Test
	//@Transactional //不入库，
	public void test1() {
		ProductCategory pc = new ProductCategory();
		pc.setCategoryName("男生最爱");
		pc.setCategoryType(2);
		productCategoryRepository.save(pc);
	}
	
	@Test
	public void test2() {
		List<Integer> cateTypeList = Arrays.asList(1,2,3,4);
		List<ProductCategory> list = productCategoryRepository.findByCategoryTypeIn(cateTypeList);
		Assert.assertNotEquals(0, list.size());
	}
	
	
}
