package com.ecart.service;

import org.springframework.stereotype.Component;

import com.ecart.model.OrderRequest;
import com.ecart.model.CommonResponse;

@Component
public interface OrderService {
    public CommonResponse orderProduct(OrderRequest orderRequest);
}