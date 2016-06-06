package be.vdab.repositories;

import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

@Configuration
public class TestRepositoriesConfig extends RepositoriesConfig {

	@Override
	LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean factory = super.entityManagerFactory();
		
		factory.getJpaPropertyMap().put("javax.persistence.schema-generation.database.action", "create");
		
		return factory;
	}
	

}
