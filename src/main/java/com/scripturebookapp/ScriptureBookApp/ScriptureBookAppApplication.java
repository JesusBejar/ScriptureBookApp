package com.scripturebookapp.ScriptureBookApp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ScriptureBookAppApplication {
	private static final Logger log = LoggerFactory.getLogger(ScriptureBookAppApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ScriptureBookAppApplication.class, args);

		log.info("app started successfully");
		log.info("something changed");

	}

}
