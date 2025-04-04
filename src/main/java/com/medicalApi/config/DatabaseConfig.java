package com.medicalApi.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    @Bean
    public DataSource dataSource(){
        Dotenv dotenv = Dotenv.load();

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(dotenv.get("DB_URL"));
        dataSource.setUsername(dotenv.get("DB_USER"));
        dataSource.setPassword(dotenv.get("DB_PASSWORD"));

        return dataSource;

    }
}
