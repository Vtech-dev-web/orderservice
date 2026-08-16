package com.ecart.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecart.model.OrderRequest;
import com.ecart.model.CommonResponse;
import com.ecart.service.OrderService;

@RestController
@RequestMapping("/api")
public class OrderController {

	private OrderService orderService;

	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@PostMapping("/orders")
	public CommonResponse orders(@RequestBody OrderRequest orderRequest) {
		System.out.println("-------------------RestComm");

		CommonResponse orderResponse = orderService.orderProduct(orderRequest);
		return orderResponse;


	}

}
