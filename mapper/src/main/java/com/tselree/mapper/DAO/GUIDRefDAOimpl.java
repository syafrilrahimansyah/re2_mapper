package com.tselree.mapper.DAO;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

public class GUIDRefDAOimpl implements GUIDRefDAO{
	JdbcTemplate jdbcTemplate;
	
	public GUIDRefDAOimpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public String getValue(String GUID) {
		try {
			String sql = "SELECT value FROM public.extc_guid_references WHERE guid = '"+GUID+"'";
			return jdbcTemplate.queryForObject(sql, String.class);
		}
		catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

}
