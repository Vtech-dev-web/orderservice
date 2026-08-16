package com.ecart.dao;

import org.springframework.stereotype.Component;

import com.ecart.model.OrderRequest;

@Component
public interface OrderDao {

	 public Boolean orderProduct(OrderRequest orderRequest, Long orderId);
}
