package com.example.infogame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.dialect.springdata.SpringDataDialect;

@SpringBootApplication
public class InfogameApplication {

	public static void main(String[] args) {
		SpringApplication.run(InfogameApplication.class, args);
	}

	@Bean
	public SpringDataDialect springDataDialect(){
		return new SpringDataDialect();
	}
	
}
