package com.ecart.service;

import org.springframework.stereotype.Service;

import com.ecart.dao.OrderDao;
import com.ecart.model.OrderRequest;
import com.ecart.model.CommonResponse;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.jms.core.JmsTemplate;

@Service
public class OrderServiceImpl implements OrderService {

	private OrderDao orderDao;
	private RestTemplate restTemplate;
	private final JmsTemplate jmsTemplate;

	public OrderServiceImpl(OrderDao orderDao, RestTemplate restTemplate, JmsTemplate jmsTemplate) {
		this.orderDao = orderDao;
		this.restTemplate=restTemplate;
		this.jmsTemplate=jmsTemplate;
	}

	@Override
	public CommonResponse orderProduct(OrderRequest orderRequest) {
		CommonResponse orderResponse = new CommonResponse();

		Long orderId=generateId();
		Boolean orderRes = orderDao.orderProduct(orderRequest,orderId);
		System.out.println("-------------------Order Service----------" +orderRes);

		if(orderRes) {
			System.out.println("--------------SImpl");
			String url = "http://localhost:8082/payments/api/payments/{orderId}";

			restTemplate.postForObject(url, null,CommonResponse.class, orderId);
			System.out.println("-----------------rest Servive---------");
			orderResponse.setStatus("Success");
			orderResponse.setMessage(orderId+ " Successfully ordered!. ");
			orderResponse.setStatusCode("101");
		}
		return orderResponse;
	}

	public Long generateId() {

		int randomNumber =
				ThreadLocalRandom.current().nextInt(1, 100000);

		String dateTime =
				LocalDateTime.now().format(
						DateTimeFormatter.ofPattern("yyyyMMddHHmmss")
				);

		return Long.parseLong(dateTime + randomNumber);
	}
}