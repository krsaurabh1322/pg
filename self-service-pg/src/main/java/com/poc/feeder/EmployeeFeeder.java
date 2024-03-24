package com.poc.feeder;

import com.google.inject.Inject;
import com.poc.model.Employee;
import org.bson.Document;

import java.util.concurrent.ExecutorService;

public class EmployeeFeeder extends GenericEntityFeeder<Employee> {

    @Inject
    public EmployeeFeeder(GenericMongoRepository<Employee> mongoRepository, GenericJpaRepository<Employee> jpaRepository, ExecutorService executorService) {
        super(mongoRepository, jpaRepository, executorService);
    }

    @Override
    protected Employee parseEntity(String record) {
        return null;
    }

    @Override
    protected Employee parseEntity(Document doc) {
        // Implement logic to convert Document to Employee object
        Employee employee = new Employee();
        // ... parsing logic ...
        return employee;
    }
}
