package com.vs.alafe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.vs.alafe.model.entity")
@EnableJpaRepositories(basePackages = "com.vs.alafe.model.dao")
public class AlafeApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlafeApplication.class, args);
	}

}
