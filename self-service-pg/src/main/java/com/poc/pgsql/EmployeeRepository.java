package com.poc.pgsql;

//EmployeeRepository.java
import org.springframework.data.jpa.repository.JpaRepository;

import com.poc.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
