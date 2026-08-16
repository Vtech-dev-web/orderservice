package com.ecart.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ecart.model.OrderResponse;
import com.ecart.service.OrderFindIdService;

@ExtendWith(MockitoExtension.class)
class OrderFindIdControllerTest {

    @Mock
    private OrderFindIdService orderService;

    @InjectMocks
    private OrderFindIdController orderFindIdController;

    @Test
    void getOrderById_Success() {

        Long orderId = 101L;

        OrderResponse expectedResponse = new OrderResponse();

        when(orderService.getOrderById(orderId))
                .thenReturn(expectedResponse);

        ResponseEntity<OrderResponse> response =
                orderFindIdController.getOrderById(orderId);

        assertNotNull(response);

        assertEquals(
                HttpStatus.OK,
                response.getStatusCode()
        );

        assertEquals(
                expectedResponse,
                response.getBody()
        );

        verify(orderService).getOrderById(orderId);
    }

    @Test
    void getOrderById_OrderNotFound() {

        Long orderId = 101L;

        when(orderService.getOrderById(orderId))
                .thenReturn(null);

        ResponseEntity<OrderResponse> response =
                orderFindIdController.getOrderById(orderId);

        assertNotNull(response);

        assertEquals(
                HttpStatus.OK,
                response.getStatusCode()
        );

        assertEquals(
                null,
                response.getBody()
        );

        verify(orderService).getOrderById(orderId);
    }
}