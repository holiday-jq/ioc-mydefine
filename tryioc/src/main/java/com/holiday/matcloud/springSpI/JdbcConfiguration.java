package com.holiday.matcloud.springSpI;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JdbcConfiguration {

	// 或者直接在EnableJdbc注解中 import DataSourceRegisterPostProcessor.class
	@Bean
	public DataSourceRegisterPostProcessor dataSourceRegisterPostProcessor() {
		return new DataSourceRegisterPostProcessor();
	}
}
