package com.poc.pgsql;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poc.model.Project;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
    @Override
    List<Project> findAll();
}