package com.smarthouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SmarthouseApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmarthouseApplication.class, args);
	}

}
