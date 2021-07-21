/**
 * 
 */
package com.mmt.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@ComponentScan(basePackages = "com.mmt")
@EnableJpaRepositories("com.mmt.repository")
@EntityScan(basePackages = "com.mmt.Entity")
public class MMTApp  {

	private static final Logger logger = LoggerFactory.getLogger(MMTApp.class);

	public static void main(String[] args) {
		SpringApplication.run(MMTApp.class, args);
		logger.debug("Start App");
	}

	/*
	 * @Override protected SpringApplicationBuilder
	 * configure(SpringApplicationBuilder application) { return
	 * application.sources(MMTApp.class); }
	 */

}
