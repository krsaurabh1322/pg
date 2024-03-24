package com.poc.feeder;

import org.bson.Document;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface GenericMongoRepository<T> {

    List<Document> findAll();
    Slice<Document> findAll(PageRequest pageRequest);

    // Optional methods for finer-grained control (implement as needed)
    // List<T> findByCriteria(Criteria criteria);
    // T findById(String id);
    // etc.
}
