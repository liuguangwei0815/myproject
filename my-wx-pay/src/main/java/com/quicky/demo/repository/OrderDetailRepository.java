package com.quicky.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quicky.demo.bean.OrderDetail;

/**
 * Created by 廖师兄
 * 2017-06-11 17:28
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {

    List<OrderDetail> findByOrderId(String orderId);
}
