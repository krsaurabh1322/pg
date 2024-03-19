package com.poc.pgsql;

import com.google.inject.Inject;
import com.google.inject.Provider;

import javax.persistence.EntityManagerFactory;

public class EntityManagerFactoryProvider implements Provider<EntityManagerFactory> {

    private final EntityManagerFactory entityManagerFactory;

    @Inject
    public EntityManagerFactoryProvider(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public EntityManagerFactory get() {
        return entityManagerFactory;
    }
}
