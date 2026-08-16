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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ecart.model.CommonResponse;
import com.ecart.service.CancelOrderService;

@ExtendWith(MockitoExtension.class)
public class CancelOrderControllerTest {
    @Mock
    private CancelOrderService cancelOrderService;

    @InjectMocks
    private CancelOrderController cancelOrderController;

    private CommonResponse commonResponse;

    @BeforeEach
    void setUp() {
        commonResponse = new CommonResponse();
    }

    @Test
    void cancelOrder_success() {

        Long orderId = 1001L;

        when(cancelOrderService.cancelOrder(orderId))
                .thenReturn(commonResponse);

        ResponseEntity<CommonResponse> response =
                cancelOrderController.cancelOrder(orderId);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(commonResponse, response.getBody());

        verify(cancelOrderService).cancelOrder(orderId);
    }
}