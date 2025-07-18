package com.scripturebookapp.ScriptureBookApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import yoMomma.WelcomeMsg;

@SpringBootApplication
public class ScriptureBookAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScriptureBookAppApplication.class, args);

		var welcomeMsg = new WelcomeMsg();
		System.out.println(welcomeMsg.getWelcomeMsg());
	}

}
