package fr.dauphine.books.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@ConfigurationProperties
@PropertySources(value = {
		@PropertySource(value = "classpath:books.properties", ignoreResourceNotFound = true),
		@PropertySource(value = "classpath:books-data.properties", ignoreResourceNotFound = true)})
public class PropertiesConfig {

    //To resolve ${} in @Value
	@Bean
	static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		final PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
		configurer.setOrder(1);
		return configurer;
	}

}