package com.project.subwate_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:/keys.properties")
public class SubwateBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SubwateBackendApplication.class, args);
	}

}
