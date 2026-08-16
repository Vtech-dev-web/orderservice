package com.ecart.service;

import org.springframework.stereotype.Service;

import com.ecart.dao.CancelOrderDao;
import com.ecart.model.CommonResponse;

@Service
public class CancelOrderServiceImpl implements CancelOrderService{

	private CancelOrderDao cancelOrderDao;
	public CancelOrderServiceImpl(CancelOrderDao cancelOrderDao) {
		this.cancelOrderDao=cancelOrderDao;
	}
	
	@Override
	public CommonResponse cancelOrder(Long orderId) {
		CommonResponse orderResponse = new CommonResponse();

		Boolean response = cancelOrderDao.cancelOrder(orderId);
		 if(response) {
				orderResponse.setStatus("Success");
				orderResponse.setMessage(orderId +" Successfully Cancelled!. ");
				orderResponse.setStatusCode("102");
			}
			return orderResponse;
	}

}
