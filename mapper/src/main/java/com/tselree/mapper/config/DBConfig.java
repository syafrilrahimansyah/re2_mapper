package com.tselree.mapper.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.tselree.mapper.DAO.EntityDAO;
import com.tselree.mapper.DAO.EntityDAOimpl;
import com.tselree.mapper.DAO.GUIDRefDAO;
import com.tselree.mapper.DAO.GUIDRefDAOimpl;
import com.tselree.mapper.DAO.MapListDAO;
import com.tselree.mapper.DAO.MapListDAOimpl;

@Configuration
@ComponentScan(basePackages="com.tselree.mapper")
public class DBConfig {
	@Bean()
    public DataSource getDataSource2() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/extractor2?serverTimezone=UTC&useLegacyDatetimeCode=false");
        dataSource.setUsername("pmauser");
        dataSource.setPassword("alvin147");
         
        return dataSource;
    }
	@Bean()
    public DataSource getDataSource3() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://parakoder.com:3306/parakode_re2_entity?serverTimezone=UTC&useLegacyDatetimeCode=false");
        dataSource.setUsername("parakode_re2_entity");
        dataSource.setPassword("re2Ent1ty");
         
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
