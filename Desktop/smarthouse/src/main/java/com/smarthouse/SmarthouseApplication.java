package com.smarthouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import org.springframework.cache.annotation.EnableCaching;


@EntityScan(basePackages = "com.smarthouse.model")
@SpringBootApplication(scanBasePackages = "com.smarthouse")

@EnableCaching
public class SmarthouseApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmarthouseApplication.class, args);
	}
}
