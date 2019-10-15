package com.quicky.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quicky.demo.bean.ProductInfo;

/**
 * Created by 廖师兄
 * 2017-05-09 11:39
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {

    List<ProductInfo> findByProductStatus(Integer productStatus);
}
