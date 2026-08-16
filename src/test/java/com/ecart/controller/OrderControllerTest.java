package com.ecart.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ecart.model.CommonResponse;
import com.ecart.model.OrderRequest;
import com.ecart.service.OrderService;

@ExtendWith(MockitoExtension.class)
class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    private OrderRequest orderRequest;
    private CommonResponse commonResponse;

    @BeforeEach
    void setUp() {

        orderRequest = new OrderRequest();

        commonResponse = new CommonResponse();
    }

    @Test
    void testOrders_Success() {

        when(orderService.orderProduct(orderRequest))
                .thenReturn(commonResponse);

        CommonResponse response =
                orderController.orders(orderRequest);

        assertNotNull(response);
        assertEquals(commonResponse, response);

        verify(orderService).orderProduct(orderRequest);
    }
}