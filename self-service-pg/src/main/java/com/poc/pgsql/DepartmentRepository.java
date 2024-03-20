package com.poc.pgsql;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poc.model.Department;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
