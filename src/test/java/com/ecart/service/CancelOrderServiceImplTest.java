package com.ecart.service;

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

import com.ecart.dao.CancelOrderDao;
import com.ecart.model.CommonResponse;

@ExtendWith(MockitoExtension.class)
class CancelOrderServiceImplTest {

    @Mock
    private CancelOrderDao cancelOrderDao;

    @InjectMocks
    private CancelOrderServiceImpl cancelOrderService;

    private Long orderId;

    @BeforeEach
    void setUp() {
        orderId = 101L;
    }

    @Test
    void cancelOrder_Success() {

        when(cancelOrderDao.cancelOrder(orderId))
                .thenReturn(true);

        CommonResponse response =
                cancelOrderService.cancelOrder(orderId);

        assertNotNull(response);
        assertEquals("Success", response.getStatus());
        assertEquals(
                "101 Successfully Cancelled!. ",
                response.getMessage()
        );
        assertEquals("102", response.getStatusCode());

        verify(cancelOrderDao).cancelOrder(orderId);
    }

    @Test
    void cancelOrder_Failed() {

        when(cancelOrderDao.cancelOrder(orderId))
                .thenReturn(false);

        CommonResponse response =
                cancelOrderService.cancelOrder(orderId);

        assertNotNull(response);

        assertEquals(null, response.getStatus());
        assertEquals(null, response.getMessage());
        assertEquals(null, response.getStatusCode());

        verify(cancelOrderDao).cancelOrder(orderId);
    }
}