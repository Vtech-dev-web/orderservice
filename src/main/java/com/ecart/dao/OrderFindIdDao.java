package com.ecart.dao;

import org.springframework.stereotype.Component;

import com.ecart.model.OrderResponse;

@Component
public interface OrderFindIdDao {
	public OrderResponse getOrderById(Long orderId);
}
