package com.poc.pgsql;

//DepartmentRepository.java
import org.springframework.data.jpa.repository.JpaRepository;

import com.poc.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
