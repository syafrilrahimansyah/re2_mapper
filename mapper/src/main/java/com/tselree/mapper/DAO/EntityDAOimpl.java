package com.tselree.mapper.DAO;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

public class EntityDAOimpl implements EntityDAO{
	JdbcTemplate jdbcTemplate;
	
	public EntityDAOimpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public String getValue(String table, String column, int key) {
		try {
			String sql = "SELECT "+column+" FROM `"+table+"` WHERE ID = "+key+"";
			return jdbcTemplate.queryForObject(sql, String.class);
		}
		catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public void updateValue(String table, String column, int key, String value) {
		String sql = "UPDATE `"+table+"` SET "+column+" = '"+value+"' WHERE ID = '"+key+"'";
		jdbcTemplate.update(sql);
	}

}
