package com.poc.pgsql;

import com.google.inject.AbstractModule;
import org.springframework.guice.module.BeanFactoryProvider;
import org.springframework.guice.module.SpringModule;

public class GuiceModule extends AbstractModule {

	@Override
	protected void configure() {
		// Bind DataSourceConfig (Register Spring configuration)
		install(new SpringModule(BeanFactoryProvider.from(DataSourceConfig.class)));

		bind(DataPopulator.class);
		bind(QueryClient.class);
	}
}
