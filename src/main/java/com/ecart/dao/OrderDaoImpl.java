package com.ecart.dao;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import com.ecart.model.OrderRequest;

@Service
public class OrderDaoImpl implements OrderDao {

	private NamedParameterJdbcTemplate mysqlJdbcTemplate;

	public OrderDaoImpl(NamedParameterJdbcTemplate mysqlJdbcTemplate) {
		this.mysqlJdbcTemplate = mysqlJdbcTemplate;
	}

	public Boolean orderProduct(OrderRequest orderRequest, Long orderId){
		String sql = "INSERT INTO ecart.orders ( order_id, customerid, total_amount, currency, status, payment_method, shipping_address) VALUES"
				+ " (:orderId,:customerId,:amount,:currency, :status, :paymentmethod, :shippingaddress)";
		
		try {
		MapSqlParameterSource newparams = new MapSqlParameterSource()
				.addValue("orderId", orderId)
				.addValue("customerId", orderRequest.getCustomerId())
				.addValue("amount", orderRequest.getAmount())
				.addValue("currency", orderRequest.getCurrency())
				.addValue("status", "PENDING")
				.addValue("paymentmethod", "Card")
				.addValue("shippingaddress", "MP");

		int val = mysqlJdbcTemplate.update(sql, newparams);
		if(val>0) {
			return true;
		}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		
		return false;
	    }
}
