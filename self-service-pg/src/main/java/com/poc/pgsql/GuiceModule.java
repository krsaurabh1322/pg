package com.poc.pgsql;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provider;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class GuiceModule extends AbstractModule {

	@Override
	protected void configure() {
		// Bind EntityManagerFactory
		bind(EntityManagerFactory.class).toProvider(EntityManagerFactoryProvider.class).asEagerSingleton();

		// Bind EntityManager
		bind(EntityManager.class).toProvider(EntityManagerProvider.class).asEagerSingleton();

		// Bind Spring Data JPA Repositories
		// Bind repositories
		bind(EmployeeRepository.class).toProvider(EmployeeRepositoryProvider.class).asEagerSingleton();
        bind(DepartmentRepository.class).toProvider(DepartmentRepositoryProvider.class).asEagerSingleton();
        bind(ProjectRepository.class).toProvider(ProjectRepositoryProvider.class).asEagerSingleton();
        
        // Bind DataPopulator
		bind(DataPopulatorPG.class).asEagerSingleton();
	}	
	
	static class EmployeeRepositoryProvider implements Provider<EmployeeRepository> {
	    private final Provider<EntityManager> entityManagerProvider;

	    @Inject
	    public EmployeeRepositoryProvider(Provider<EntityManager> entityManagerProvider) {
	        this.entityManagerProvider = entityManagerProvider;
	    }

	    @Override
	    public EmployeeRepository get() {
	        return new JpaRepositoryProvider<>(entityManagerProvider.get(), EmployeeRepository.class).get();
	    }
	}
	
	static class DepartmentRepositoryProvider implements Provider<DepartmentRepository> {
	    private final Provider<EntityManager> entityManagerProvider;

	    @Inject
	    public DepartmentRepositoryProvider(Provider<EntityManager> entityManagerProvider) {
	        this.entityManagerProvider = entityManagerProvider;
	    }

	    @Override
	    public DepartmentRepository get() {
	        return new JpaRepositoryProvider<>(entityManagerProvider.get(), DepartmentRepository.class).get();
	    }
	}
	
	static class ProjectRepositoryProvider implements Provider<ProjectRepository> {
	    private final Provider<EntityManager> entityManagerProvider;

	    @Inject
	    public ProjectRepositoryProvider(Provider<EntityManager> entityManagerProvider) {
	        this.entityManagerProvider = entityManagerProvider;
	    }

	    @Override
	    public ProjectRepository get() {
	        return new JpaRepositoryProvider<>(entityManagerProvider.get(), ProjectRepository.class).get();
	    }
	}
}
