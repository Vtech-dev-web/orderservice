package com.ecart.service;

import org.springframework.stereotype.Component;

import com.ecart.model.OrderResponse;

@Component
public interface OrderFindIdService {

	public OrderResponse getOrderById(Long orderId);
}
