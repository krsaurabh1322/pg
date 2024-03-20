package com.poc.pgsql;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poc.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Override
    List<Employee> findAll();
}
