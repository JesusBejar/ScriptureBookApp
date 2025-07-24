package com.scripturebookapp.ScriptureBookApp;

import com.scripturebookapp.run.Run;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.scripturebookapp"})
public class ScriptureBookAppApplication {
	private static final Logger log = LoggerFactory.getLogger(ScriptureBookAppApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ScriptureBookAppApplication.class, args);

	}

	@Bean
	CommandLineRunner runner() {
		return args -> {
			Run runOne = new Run(
				1,
				"Fighting Run",
				java.time.LocalDateTime.now(),
				java.time.LocalDateTime.now().plusMinutes(30),
				3
			);
			log.info("Run One : {}", runOne);
		};
	}

}
