package com.poc.pgsql;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

import java.util.List;

public class QueryClient {
	private final GenericQueryService genericQueryService;
    @Inject
    public QueryClient(GenericQueryService genericQueryService) {
        this.genericQueryService = genericQueryService;
    }

    public void executeJoinQueryAndPrintResult() {
        // Define your join query dynamically
        String joinQuery = "SELECT e.employeeId, e.employeeName, d.departmentName, p.projectName " +
                           "FROM Employee e " +
                           "JOIN Department d ON e.departmentId = d.departmentId " +
                           "JOIN Project p ON e.projectId = p.projectId";

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
        DataPopulator dataPopulator = injector.getInstance(DataPopulator.class);

        // Populate PostgreSQL tables
        dataPopulator.populatePostgresTables();

        System.out.println("Sample data populated in PostgreSQL tables successfully.");

        // Get QueryClient instance
        QueryClient queryClient = injector.getInstance(QueryClient.class);

        // Execute join query and print result
        queryClient.executeJoinQueryAndPrintResult();
    }
}
