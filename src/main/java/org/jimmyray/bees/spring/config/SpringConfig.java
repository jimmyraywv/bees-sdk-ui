package org.jimmyray.bees.spring.config;

import org.jimmyray.bees.services.BeesAppService;
import org.jimmyray.bees.services.BeesAppServiceImpl;
import org.jimmyray.bees.services.BeesServicesService;
import org.jimmyray.bees.services.BeesServicesServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

	public @Bean
	BeesAppService beesAppService() {
		return new BeesAppServiceImpl();
	}
	
	public @Bean
	BeesServicesService beesServicesService() {
		return new BeesServicesServiceImpl();
	}
}
