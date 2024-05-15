package com.multitenancy.tenant;

import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {

    @Bean
    public Map<Object, Object> tenantDataSources() {
        Map<Object, Object> dataSources = new HashMap<>();
        dataSources.put("tenant1",
                DataSourceBuilder.create()
                        .url("jdbc:postgresql://localhost:5433/tenant1")
                        .username("postgres")
                        .password("password")
                        .build()
        );

        return dataSources;
    }

    @Bean
    public DataSource dataSource(Map<Object, Object> tenantDataSources) {
        DataSource defaultDataSource = DataSourceBuilder.create()
                .url("jdbc:postgresql://localhost:5433/default")
                .username("postgres")
                .password("password")
                .build();
        return new MultiTenantDataSource(defaultDataSource, tenantDataSources);
    }
}

