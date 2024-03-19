package com.poc.pgsql;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

import java.util.List;

public class QueryClient {
	private final GenericQueryService genericQueryService;

    public QueryClient(GenericQueryService genericQueryService) {
        this.genericQueryService = genericQueryService;
    }

    public void executeJoinQueryAndPrintResult() {
        // Define your join query dynamically
        String joinQuery = "SELECT e.employee_id, e.employee_name, d.department_name, p.project_name " +
                           "FROM EmployeeEntity e " +
                           "JOIN DepartmentEntity d ON e.department_id = d.department_id " +
                           "JOIN ProjectEntity p ON e.project_id = p.project_id";

        // Execute join query using GenericQueryService
        List<Object[]> result = genericQueryService.executeJoinQuery(joinQuery);

        // Print the result in a pretty format
        System.out.println("Result of Join Query:");
        System.out.println("==============================================");
        for (Object[] row : result) {
            System.out.printf("Employee ID: %s, Employee Name: %s, Department: %s, Project: %s%n",
                    row[0], row[1], row[2], row[3]);
        }
        System.out.println("==============================================");
    }
    
    public static void main(String[] args) {
        // Create Guice injector
        Injector injector = Guice.createInjector(new GuiceModule());
        
     // Get DataPopulator instance
        DataPopulatorPG dataPopulator = injector.getInstance(DataPopulatorPG.class);

        // Populate PostgreSQL tables
        dataPopulator.populatePostgresTables();

        System.out.println("Sample data populated in PostgreSQL tables successfully.");

        // Get QueryClient instance
        QueryClient queryClient = injector.getInstance(QueryClient.class);

        // Execute join query and print result
        queryClient.executeJoinQueryAndPrintResult();
    }
}
