package com.poc.pgsql;

import com.google.inject.Inject;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class GenericQueryService {

    private final EntityManager entityManager;

    @Inject
    public GenericQueryService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Object[]> executeJoinQuery(String dynamicSql) {
        Query query = entityManager.createNativeQuery(dynamicSql);
        return query.getResultList();
    }
}
