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

@ExtendWith(MockitoExtension.class)
class CancelOrderDaoImplTest {

    @Mock
    private NamedParameterJdbcTemplate mysqlJdbcTemplate;

    @InjectMocks
    private CancelOrderDaoImpl cancelOrderDao;

    @Test
    void cancelOrder_Success() {

        Long orderId = 101L;

        when(mysqlJdbcTemplate.update(
                anyString(),
                any(MapSqlParameterSource.class)))
                .thenReturn(1);

        Boolean result =
                cancelOrderDao.cancelOrder(orderId);

        assertTrue(result);

        verify(mysqlJdbcTemplate).update(
                anyString(),
                any(MapSqlParameterSource.class));
    }

    @Test
    void cancelOrder_OrderNotFound() {

        Long orderId = 101L;

        when(mysqlJdbcTemplate.update(
                anyString(),
                any(MapSqlParameterSource.class)))
                .thenReturn(0);

        Boolean result =
                cancelOrderDao.cancelOrder(orderId);

        assertFalse(result);

        verify(mysqlJdbcTemplate).update(
                anyString(),
                any(MapSqlParameterSource.class));
    }

    @Test
    void cancelOrder_DatabaseException() {

        Long orderId = 101L;

        when(mysqlJdbcTemplate.update(
                anyString(),
                any(MapSqlParameterSource.class)))
                .thenThrow(new RuntimeException("Database error"));

        Boolean result =
                cancelOrderDao.cancelOrder(orderId);

        assertFalse(result);

        verify(mysqlJdbcTemplate).update(
                anyString(),
                any(MapSqlParameterSource.class));
    }
}