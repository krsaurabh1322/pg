package com.poc.pgsql;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

public class EntityManagerFactoryProvider implements Provider<EntityManagerFactory> {

    @Inject
    private DataSourceConfig dataSourceConfig;

    @Override
    public EntityManagerFactory get() {
        DataSource dataSource = dataSourceConfig.dataSource();
        LocalContainerEntityManagerFactoryBean factoryBean = dataSourceConfig.entityManagerFactory(dataSource);
        factoryBean.afterPropertiesSet();
        return factoryBean.getObject();
    }
}