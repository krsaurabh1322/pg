package com.poc.pgsql;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;

import com.google.inject.Provider;

public class JpaRepositoryProvider<T extends JpaRepository<?, ?>, E> implements Provider<T> {

    private final JpaRepositoryFactory jpaRepositoryFactory;
    private final Class<T> repositoryInterface;

    public JpaRepositoryProvider(EntityManager entityManager, Class<T> repositoryInterface) {
        this.jpaRepositoryFactory = new JpaRepositoryFactory(entityManager);
        this.repositoryInterface = repositoryInterface;
    }

    @Override
    public T get() {
        return jpaRepositoryFactory.getRepository(repositoryInterface);
    }
}
