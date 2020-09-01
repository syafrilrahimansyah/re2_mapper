package com.tselree.mapper.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.tselree.mapper.model.MapList;

public class MapListDAOimpl implements MapListDAO{

	JdbcTemplate jdbcTemplate;
	
	public MapListDAOimpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Integer getNew() {
		try {
			String sql = "SELECT MIN(ID) FROM MAP_LIST WHERE STAGE = 'ext'";
			return jdbcTemplate.queryForObject(sql, Integer.class);
		}
		catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public MapList getMap(int id){

	    String sql = "SELECT * FROM MAP_LIST WHERE ID = "+id;
	    
	    return jdbcTemplate.query(
	    	      sql,
	    	      resultSet -> {
	    	    	 resultSet.next();
	    	         final MapList mapList = new MapList();
	    	         mapList.setTable(resultSet.getString("TABLE"));
	    	         mapList.setColumn(resultSet.getString("COLUMN"));
	    	         mapList.setKey(resultSet.getInt("KEY"));
	    	         mapList.setDelimiter(resultSet.getString("DELIMITER"));
	    	         return mapList;
	    	      }
	    	);
	}

	@Override
	public void updateStage(int id) {
		String sql = "UPDATE MAP_LIST SET STAGE = 'map' WHERE ID = '"+id+"'";
		jdbcTemplate.update(sql);
	}

}
