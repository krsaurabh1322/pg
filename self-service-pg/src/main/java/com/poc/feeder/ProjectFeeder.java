package com.poc.feeder;

import com.poc.model.Project;
import org.bson.Document;

import java.util.concurrent.ExecutorService;

public class ProjectFeeder extends GenericEntityFeeder<Project> {

    public ProjectFeeder(GenericMongoRepository<Project> mongoRepository, GenericJpaRepository<Project> jpaRepository, ExecutorService executorService) {
        super(mongoRepository, jpaRepository, executorService);
    }

    @Override
    protected Project parseEntity(String record) {
        return null;
    }

    @Override
    protected Project parseEntity(Document doc) {
        // Implement logic to convert Document to Project object
        Project project = new Project();
        // ... parsing logic ...
        return project;
    }
}