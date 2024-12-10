package com.alquilaya;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"com.alquilaya.entity"})
@EnableJpaRepositories(basePackages = {"com.alquilaya.jpa"})
@ComponentScan(basePackages = {"com.alquilaya.controller",
		"com.alquilaya.service","com.alquilaya.dao","com.alquilaya.dto","com.alquilaya","com.alquilaya.util","com.alquilaya.security"})
public class AlquilayaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlquilayaApplication.class, args);
	}

}
