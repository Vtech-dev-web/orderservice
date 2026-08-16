package com.ecart.dao;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.ecart.model.OrderRequest;

@ExtendWith(MockitoExtension.class)
class OrderDaoImplTest {

    @Mock
    private NamedParameterJdbcTemplate mysqlJdbcTemplate;

    @InjectMocks
    private OrderDaoImpl orderDao;

    @Test
    void orderProduct_Success() {


        OrderRequest orderRequest = new OrderRequest();

        orderRequest.setCustomerId("101");
        orderRequest.setAmount("5000");
        orderRequest.setCurrency("INR");

        Long orderId = 1001L;

        when(mysqlJdbcTemplate.update(
                anyString(),
                any(MapSqlParameterSource.class)))
                .thenReturn(1);

        Boolean result =
                orderDao.orderProduct(orderRequest, orderId);


        assertTrue(result);

        verify(mysqlJdbcTemplate).update(
                anyString(),
                any(MapSqlParameterSource.class));
    }

    @Test
    void orderProduct_InsertFailed() {

        OrderRequest orderRequest = new OrderRequest();

        orderRequest.setCustomerId("101");
        orderRequest.setAmount("5000");
        orderRequest.setCurrency("INR");

        Long orderId = 1001L;

        when(mysqlJdbcTemplate.update(
                anyString(),
                any(MapSqlParameterSource.class)))
                .thenReturn(0);

        Boolean result =
                orderDao.orderProduct(orderRequest, orderId);

        assertFalse(result);

        verify(mysqlJdbcTemplate).update(
                anyString(),
                any(MapSqlParameterSource.class));
    }

    @Test
    void orderProduct_DatabaseException() {

        OrderRequest orderRequest = new OrderRequest();

        orderRequest.setCustomerId("101");
        orderRequest.setAmount("5000");
        orderRequest.setCurrency("INR");

        Long orderId = 1001L;

        when(mysqlJdbcTemplate.update(
                anyString(),
                any(MapSqlParameterSource.class)))
                .thenThrow(new RuntimeException("Database error"));

        Boolean result =
                orderDao.orderProduct(orderRequest, orderId);

        assertFalse(result);

        verify(mysqlJdbcTemplate).update(
                anyString(),
                any(MapSqlParameterSource.class));
    }
}