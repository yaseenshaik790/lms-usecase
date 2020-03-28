package com.leavedemo.leavemanagementsystem.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * These class is used for to test the spring boot api's 
 * @author ShaikYaseen
 */
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

	private Logger logger = LoggerFactory.getLogger(SwaggerConfiguration.class);

	/**
	 * @Bean is used to create bean for these class
	 * 
	 * @return Docker object
	 */
	@Bean
	public Docket getDocket() {
		logger.info("Swagger enabled ");

		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any()).build();
	}
}
