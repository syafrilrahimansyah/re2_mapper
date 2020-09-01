package com.tselree.mapper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.tselree.mapper.DAO.EntityDAO;
import com.tselree.mapper.DAO.GUIDRefDAO;
import com.tselree.mapper.DAO.MapListDAO;
import com.tselree.mapper.model.MapList;


@Component
public class MainTask {
	@Autowired
	private MapListDAO mapListDAO;
	@Autowired
	private EntityDAO entityDAO;
	@Autowired
	private GUIDRefDAO guidRefDAO;
	
	private static final Logger log = LoggerFactory.getLogger(MainTask.class);
	@Scheduled(fixedRate = 500)
	public void task() throws Exception{
		Integer id = mapListDAO.getNew();
		if(id != null) {
			log.info("New Map List Found");
			MapList mapList = mapListDAO.getMap(id);
			String preValue = entityDAO.getValue(mapList.getTable(), mapList.getColumn(), mapList.getKey());
			//get DELIMITER
			//IF EXIST
			//Explode Value and FOR
			if(!mapList.getDelimiter().equals("")) {
				log.info("with delimiter");
				String arrpreValue[] = preValue.split(mapList.getDelimiter());
				List<String> listPreValue = new ArrayList<>();
				listPreValue = Arrays.asList(arrpreValue);
				List<String> listPostValue = new ArrayList<>();
				for(String eachPreValue : listPreValue) {
					String postValue = guidRefDAO.getValue(eachPreValue);
					if(postValue==null) {
						log.info("GUID Not Found");
						listPostValue.add(eachPreValue);
					}else {
						listPostValue.add(postValue);
					}
				}
				String postValue = String.join(mapList.getDelimiter(), listPostValue);
				entityDAO.updateValue(mapList.getTable(), mapList.getColumn(), mapList.getKey(), postValue);
				
			}else {
				// ELSE BELOW
				//################################## WITHOUT DELIMITER ##########################################
				String postValue = guidRefDAO.getValue(preValue);
				if(postValue==null) {
					log.info("GUID Not Found");
				}else {
					entityDAO.updateValue(mapList.getTable(), mapList.getColumn(), mapList.getKey(), postValue);
				}
				//###############################################################################################
			}			
			mapListDAO.updateStage(id);
		}else {
			log.info("Searching Map List");
		}
	}
}
