package com.poc.pgsql;

import com.google.inject.Inject;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GenericQueryService {

    private final EntityManagerFactory entityManagerFactory;

    @Inject
    public GenericQueryService(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public List<Object[]> executeJoinQuery(String dynamicSql) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Query query = entityManager.createNativeQuery(dynamicSql);
            return query.getResultList();
        } finally {
            entityManager.close();
        }
    }
}
