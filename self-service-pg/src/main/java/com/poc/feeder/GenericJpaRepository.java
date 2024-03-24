package com.poc.feeder;

import java.util.List;

public interface GenericJpaRepository<T> {

    <S extends T> S save(S entity);

    <S extends T> List<S> saveAll(Iterable<S> entities);

    // Optional methods for completeness (implement as needed)
    // Optional<T> findById(Object id);
    // etc.
}
