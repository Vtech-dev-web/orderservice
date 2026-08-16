package com.ecart.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecart.model.CommonResponse;
import com.ecart.service.CancelOrderService;

@RestController
@RequestMapping("/api/orders")
public class CancelOrderController {

	private CancelOrderService cancelOrderService;

	public CancelOrderController(CancelOrderService cancelOrderService) {
		this.cancelOrderService = cancelOrderService;
	}

	@GetMapping("/{orderId}/cancel")
	public ResponseEntity<CommonResponse> cancelOrder(@PathVariable Long orderId) {
		CommonResponse commonResponse = cancelOrderService.cancelOrder(orderId);
		return ResponseEntity.ok(commonResponse);
	}

}
