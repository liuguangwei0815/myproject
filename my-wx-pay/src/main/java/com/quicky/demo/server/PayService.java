package com.quicky.demo.server;

import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;
import com.quicky.demo.dto.OrderDTO;

/**
 * 支付
 * Created by 廖师兄
 * 2017-07-04 00:53
 */
public interface PayService {

    PayResponse create(OrderDTO orderDTO);

    PayResponse notify(String notifyData);

    RefundResponse refund(OrderDTO orderDTO);
}
