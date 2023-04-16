package fr.dauphine.data.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EntityScan("fr.dauphine.data.domain")
@EnableJpaRepositories("fr.dauphine.data.dao")
@EnableTransactionManagement
public class DatabaseConfiguration {

}