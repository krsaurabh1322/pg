package com.poc.pgsql;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poc.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
}