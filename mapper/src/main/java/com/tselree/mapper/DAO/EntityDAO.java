package com.tselree.mapper.DAO;

public interface EntityDAO {
	public String getValue(String table, String column, int key);
	public void updateValue(String table, String column, int key, String value);
}
