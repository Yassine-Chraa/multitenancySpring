package com.multitenancy.tenant;

import java.util.Map;
import javax.sql.DataSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class MultiTenantDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return TenantContext.getTenantId();
    }

    public MultiTenantDataSource(DataSource defaultDataSource, Map<Object, Object> tenantDataSources) {
        super.setDefaultTargetDataSource(defaultDataSource);
        super.setTargetDataSources(tenantDataSources);
        super.afterPropertiesSet();
    }
}
