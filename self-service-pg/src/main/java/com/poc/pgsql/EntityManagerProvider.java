package com.poc.pgsql;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class EntityManagerProvider implements Provider<EntityManager> {

    private final EntityManagerFactory entityManagerFactory;

    @Inject
    public EntityManagerProvider(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public EntityManager get() {
        return entityManagerFactory.createEntityManager();
    }
}
