package com.ecart.service;

import org.springframework.stereotype.Component;

import com.ecart.model.CommonResponse;

@Component
public interface CancelOrderService {

	public CommonResponse cancelOrder(Long orderId);

}
