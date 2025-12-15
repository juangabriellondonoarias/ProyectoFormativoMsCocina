package com.example.demo;

import org.springframework.boot.SpringApplication;   

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories(basePackages = "com.example.demo.repository")
@ComponentScan(basePackages = {"com.example.demo", "com.example.demo.controller", "com.example.demo.service"})

@SpringBootApplication
public class MsCocinaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsCocinaApplication.class, args);
	}
}
