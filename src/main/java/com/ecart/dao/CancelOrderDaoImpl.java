package com.ecart.dao;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class CancelOrderDaoImpl implements CancelOrderDao {

	private NamedParameterJdbcTemplate mysqlJdbcTemplate;

	public CancelOrderDaoImpl(NamedParameterJdbcTemplate mysqlJdbcTemplate) {
		this.mysqlJdbcTemplate = mysqlJdbcTemplate;
	}
	
	@Override
	public Boolean cancelOrder(Long orderId) {
		String sql = "UPDATE ecart.orders SET status = 'CANCELLED' WHERE order_id= :orderid";
		
		try {
		MapSqlParameterSource newparams = new MapSqlParameterSource().addValue("orderid", orderId);
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
