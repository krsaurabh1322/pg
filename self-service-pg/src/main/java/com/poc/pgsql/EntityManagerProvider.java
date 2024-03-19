package com.poc.pgsql;

import com.google.inject.Inject;
import com.google.inject.Provider;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class EntityManagerProvider implements Provider<EntityManager> {

    private final Provider<EntityManagerFactory> emfProvider;

    @Inject
    public EntityManagerProvider(Provider<EntityManagerFactory> emfProvider) {
        this.emfProvider = emfProvider;
    }

    @Override
    public EntityManager get() {
        return emfProvider.get().createEntityManager(); // Create EntityManager from EntityManagerFactory
    }
}