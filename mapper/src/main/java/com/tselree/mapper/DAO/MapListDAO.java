package com.tselree.mapper.DAO;

import java.sql.SQLException;

import com.tselree.mapper.model.MapList;

public interface MapListDAO {
	public Integer getNew();
	public MapList getMap(int id) throws SQLException;
	public void updateStage(int id);
}
