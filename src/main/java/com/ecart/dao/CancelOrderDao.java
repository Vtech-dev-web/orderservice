package com.ecart.dao;

import org.springframework.stereotype.Component;

@Component
public interface CancelOrderDao {

	public Boolean cancelOrder(Long orderId);
}
