package com.ecart.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.ecart.model.OrderResponse;

@ExtendWith(MockitoExtension.class)
class OrderFindIdDaoImplTest {

    @Mock
    private NamedParameterJdbcTemplate mysqlJdbcTemplate;

    @InjectMocks
    private OrderFindIdDaoImpl orderFindIdDao;

    @Test
    void getOrderById_Success() {

        Long orderId = 101L;

        OrderResponse expectedResponse = new OrderResponse();

        when(mysqlJdbcTemplate.queryForObject(
                anyString(),
                any(MapSqlParameterSource.class),
                any(BeanPropertyRowMapper.class)))
                .thenReturn(expectedResponse);

        OrderResponse actualResponse =
                orderFindIdDao.getOrderById(orderId);

        assertNotNull(actualResponse);
        assertEquals(expectedResponse, actualResponse);

        verify(mysqlJdbcTemplate).queryForObject(
                anyString(),
                any(MapSqlParameterSource.class),
                any(BeanPropertyRowMapper.class));
    }

    @Test
    void getOrderById_OrderNotFound() {

        Long orderId = 101L;

        when(mysqlJdbcTemplate.queryForObject(
                anyString(),
                any(MapSqlParameterSource.class),
                any(BeanPropertyRowMapper.class)))
                .thenThrow(new RuntimeException("Order not found"));

        OrderResponse actualResponse =
                orderFindIdDao.getOrderById(orderId);

        assertNull(actualResponse);

        verify(mysqlJdbcTemplate).queryForObject(
                anyString(),
                any(MapSqlParameterSource.class),
                any(BeanPropertyRowMapper.class));
    }

    @Test
    void getOrderById_DatabaseException() {

        Long orderId = 101L;

        when(mysqlJdbcTemplate.queryForObject(
                anyString(),
                any(MapSqlParameterSource.class),
                any(BeanPropertyRowMapper.class)))
                .thenThrow(new RuntimeException("Database connection failed"));

        OrderResponse actualResponse =
                orderFindIdDao.getOrderById(orderId);

        assertNull(actualResponse);

        verify(mysqlJdbcTemplate).queryForObject(
                anyString(),
                any(MapSqlParameterSource.class),
                any(BeanPropertyRowMapper.class));
    }
}