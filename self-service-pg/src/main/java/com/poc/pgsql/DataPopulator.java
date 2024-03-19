package com.poc.pgsql;

import com.google.inject.Inject;
import com.poc.model.Department;
import com.poc.model.Employee;
import com.poc.model.Project;

public class DataPopulator {
	private final EmployeeRepository employeeRepository;
	private final DepartmentRepository departmentRepository;
	private final ProjectRepository projectRepository;

	@Inject
	public DataPopulator(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository,
						 ProjectRepository projectRepository) {
		this.employeeRepository = employeeRepository;
		this.departmentRepository = departmentRepository;
		this.projectRepository = projectRepository;
	}

	public void populatePostgresTables() {
		// Populate employees table
		addEmployeeRecord(1, "A", 1, 1, 80000);
		addEmployeeRecord(2, "B", 2, 1, 90000);
		addEmployeeRecord(3, "C", 1, 2, 75000);
		addEmployeeRecord(4, "D", 2, 2, 85000);

		// Populate departments table
		addDepartmentRecord(1, "HR");
		addDepartmentRecord(2, "Engineering");

		// Populate projects table
		addProjectRecord(1, "ProjectA");
		addProjectRecord(2, "ProjectB");
	}

	private void addEmployeeRecord(Integer employeeId, String employeeName, Integer departmentId, Integer projectId,
			Integer salary) {
		Employee employeeEntity = new Employee();
		employeeEntity.setEmployeeId(employeeId);
		employeeEntity.setEmployeeName(employeeName);
		employeeEntity.setDepartmentId(departmentId);
		employeeEntity.setProjectId(projectId);
		employeeEntity.setSalary(salary);
		employeeRepository.save(employeeEntity);
	}

	private void addDepartmentRecord(Integer departmentId, String departmentName) {
		Department departmentEntity = new Department();
		departmentEntity.setDepartmentId(departmentId);
		departmentEntity.setDepartmentName(departmentName);
		departmentRepository.save(departmentEntity);
	}

	private void addProjectRecord(Integer projectId, String projectName) {
		Project projectEntity = new Project();
		projectEntity.setProjectId(projectId);
		projectEntity.setProjectName(projectName);
		projectRepository.save(projectEntity);
	}
}
