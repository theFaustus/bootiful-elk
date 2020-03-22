package com.evil.learning.bootifulelk.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = "com.evil.learning.bootifulelk")
public class BootifulElkApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootifulElkApplication.class, args);
	}

}
