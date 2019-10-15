package com.quicky.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quicky.demo.bean.SellerInfo;

/**
 * Created by 廖师兄
 * 2017-07-23 23:04
 */
public interface SellerInfoRepository extends JpaRepository<SellerInfo, String> {
    SellerInfo findByOpenid(String openid);
}
