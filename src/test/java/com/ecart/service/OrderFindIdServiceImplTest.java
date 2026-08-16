package com.ecart.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ecart.dao.OrderFindIdDao;
import com.ecart.model.OrderResponse;

@ExtendWith(MockitoExtension.class)
class OrderFindIdServiceImplTest {

    @Mock
    private OrderFindIdDao orderFindIdDao;

    @InjectMocks
    private OrderFindIdServiceImpl orderFindIdService;

    @Test
    void getOrderById_Success() {

        Long orderId = 101L;

        OrderResponse expectedResponse = new OrderResponse();

        when(orderFindIdDao.getOrderById(orderId))
                .thenReturn(expectedResponse);

        OrderResponse actualResponse =
                orderFindIdService.getOrderById(orderId);

        assertNotNull(actualResponse);
        assertEquals(expectedResponse, actualResponse);

        verify(orderFindIdDao).getOrderById(orderId);
    }

    @Test
    void getOrderById_NullResponse() {

        Long orderId = 101L;

        when(orderFindIdDao.getOrderById(orderId))
                .thenReturn(null);

        OrderResponse actualResponse =
                orderFindIdService.getOrderById(orderId);

        assertEquals(null, actualResponse);

        verify(orderFindIdDao).getOrderById(orderId);
    }
}