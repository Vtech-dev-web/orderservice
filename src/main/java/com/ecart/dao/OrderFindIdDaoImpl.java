package com.ecart.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;

import com.ecart.model.OrderResponse;

@Service
public class OrderFindIdDaoImpl implements OrderFindIdDao {

		private NamedParameterJdbcTemplate mysqlJdbcTemplate;

		public OrderFindIdDaoImpl(NamedParameterJdbcTemplate mysqlJdbcTemplate) {
			this.mysqlJdbcTemplate = mysqlJdbcTemplate;
		}

	@Override
	public OrderResponse getOrderById(Long orderId) {
		try {

			String sql = "select order_id,customerid, total_amount, currency, status, payment_method, shipping_address from orders where order_id= :orderid";
			SqlParameterSource params = new MapSqlParameterSource("orderid", orderId);
			OrderResponse orderResponse = mysqlJdbcTemplate.queryForObject(sql, params, new BeanPropertyRowMapper<>(OrderResponse.class));
			System.out.println(orderId + "count ------orders id----" + orderResponse);
			return orderResponse;
		} catch (Exception e) {
			System.out.println("-------------" + e.getMessage());

		}
		return null;
	}
}

