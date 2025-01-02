package com.example.sampleapi.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseDataSourceConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.ext.oracle.OracleDataTypeFactory;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

@Configuration
public class DatasourceConfig {

    @Bean
	public DataSource dataSource() {
		return new TransactionAwareDataSourceProxy(
				DataSourceBuilder
						.create()
						.username("test_u1")
						.password("test_u1")
						.url("jdbc:oracle:thin:@localhost:1521/FREEPDB1")
						.driverClassName("oracle.jdbc.driver.OracleDriver")
						.build());
	}

	@Bean
	public IDatabaseConnection dbUnitDatabaseConnection(DataSource dataSource) throws SQLException {
		IDatabaseConnection con = new DatabaseDataSourceConnection(dataSource, "TEST_U1");
		DatabaseConfig config = con.getConfig();
		config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new OracleDataTypeFactory());
		
		return con;
	}

}
