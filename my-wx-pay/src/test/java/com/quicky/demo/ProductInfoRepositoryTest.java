package com.quicky.demo;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.quicky.demo.bean.ProductInfo;
import com.quicky.demo.enums.ProductStatusEnum;
import com.quicky.demo.repository.ProductInfoRepository;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductInfoRepositoryTest {

	@Autowired
	ProductInfoRepository productInfoRepository;

	@Test
	public void test() {
		ProductInfo productInfo = new ProductInfo();
		productInfo.setProductId("123456");
		productInfo.setProductName("皮蛋粥");
		productInfo.setProductPrice(new BigDecimal(3.2));
		productInfo.setProductStock(100);
		productInfo.setProductDescription("很好喝的粥");
		productInfo.setProductIcon("http://xxxxx.jpg");
		productInfo.setProductStatus(0);
		productInfo.setCategoryType(2);
		ProductInfo result =  productInfoRepository.save(productInfo);
		Assert.assertNotNull(result);
		
	}

}
