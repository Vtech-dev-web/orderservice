package com.ecart.service;

import org.springframework.stereotype.Service;

import com.ecart.dao.OrderFindIdDao;
import com.ecart.model.OrderResponse;

@Service
public class OrderFindIdServiceImpl implements OrderFindIdService {

	private OrderFindIdDao orderFindIdDao;

	public OrderFindIdServiceImpl(OrderFindIdDao orderFindIdDao) {
		this.orderFindIdDao = orderFindIdDao;
	}

	@Override
	public OrderResponse getOrderById(Long orderId) {
		
		return  orderFindIdDao.getOrderById(orderId);
	}

}
