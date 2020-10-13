package com.tselree.mapper.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.tselree.mapper.DAO.EntityDAO;
import com.tselree.mapper.DAO.EntityDAOimpl;
import com.tselree.mapper.DAO.GUIDRefDAO;
import com.tselree.mapper.DAO.GUIDRefDAOimpl;
import com.tselree.mapper.DAO.MapListDAO;
import com.tselree.mapper.DAO.MapListDAOimpl;

@Configuration
@PropertySource({
    "file:src/main/resources/mapper_application.properties" 
})
public class DBConfig {
	@Autowired
    Environment env;
	
	@Bean()
    public DataSource getDataSource2() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("DB_DRIVER"));
        dataSource.setUrl(env.getProperty("DB_URL"));
        dataSource.setUsername(env.getProperty("DB_USERNAME"));
        dataSource.setPassword(env.getProperty("DB_PASSWORD"));
         
        return dataSource;
    }
	@Bean()
    public DataSource getDataSource3() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("DB_DRIVER"));
        dataSource.setUrl(env.getProperty("DB_URL"));
        dataSource.setUsername(env.getProperty("DB_USERNAME"));
        dataSource.setPassword(env.getProperty("DB_PASSWORD"));
         
        return dataSource;
    }
	@Bean()
	public MapListDAO getMapListDAO() {
		return new MapListDAOimpl(getDataSource2());
	}
	@Bean()
	public EntityDAO getEntityDAO() {
		return new EntityDAOimpl(getDataSource3());
	}
	@Bean()
	public GUIDRefDAO getGUIDRefDAO() {
		return new GUIDRefDAOimpl(getDataSource2());
	}
}
