package com.ecart.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecart.model.OrderResponse;
import com.ecart.service.OrderFindIdService;

@RestController
@RequestMapping("/api/orders")
public class OrderFindIdController {

    private final OrderFindIdService orderService;

    public OrderFindIdController(OrderFindIdService orderService) {
        this.orderService = orderService;
    }
	
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Long orderId) {
        OrderResponse order = orderService.getOrderById(orderId);
        return ResponseEntity.ok(order);
    }
}
