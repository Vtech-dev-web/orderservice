package com.ecart.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;
import org.springframework.jms.core.JmsTemplate;

import com.ecart.dao.OrderDao;
import com.ecart.model.CommonResponse;
import com.ecart.model.OrderRequest;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    @Mock
    private OrderDao orderDao;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private JmsTemplate jmsTemplate;

    @InjectMocks
    private OrderServiceImpl orderService;

    private OrderRequest orderRequest;

    @BeforeEach
    void setUp() {
        orderRequest = new OrderRequest();
    }

    @Test
    void orderProduct_success() {

        when(orderDao.orderProduct(any(OrderRequest.class), anyLong()))
                .thenReturn(true);

        CommonResponse paymentResponse = new CommonResponse();
        paymentResponse.setStatus("Success");

        when(restTemplate.postForObject(
                anyString(),
                isNull(),
                eq(CommonResponse.class),
                anyLong()
        )).thenReturn(paymentResponse);

        CommonResponse response = orderService.orderProduct(orderRequest);

        assertNotNull(response);
        assertEquals("Success", response.getStatus());
        assertEquals("101", response.getStatusCode());

        assertNotNull(response.getMessage());
        assertTrue(response.getMessage().contains("Successfully ordered"));

        verify(orderDao, times(1))
                .orderProduct(eq(orderRequest), anyLong());

        verify(restTemplate, times(1))
                .postForObject(
                        anyString(),
                        isNull(),
                        eq(CommonResponse.class),
                        anyLong()
                );
    }

    @Test
    void orderProduct_whenDaoReturnsFalse() {

        when(orderDao.orderProduct(any(OrderRequest.class), anyLong()))
                .thenReturn(false);

        CommonResponse response = orderService.orderProduct(orderRequest);

        assertNotNull(response);

        assertNull(response.getStatus());
        assertNull(response.getMessage());
        assertNull(response.getStatusCode());

        verify(orderDao, times(1))
                .orderProduct(eq(orderRequest), anyLong());

        verify(restTemplate, never())
                .postForObject(
                        anyString(),
                        any(),
                        eq(CommonResponse.class),
                        anyLong()
                );
    }

    @Test
    void generateId_shouldReturnValidId() {

        Long orderId = orderService.generateId();

        assertNotNull(orderId);
        assertTrue(orderId > 0);
    }
}