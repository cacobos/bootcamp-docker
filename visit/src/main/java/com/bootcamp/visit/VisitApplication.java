package com.bootcamp.visit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableEurekaClient
@Import(model.Visit.class)
@EntityScan(basePackageClasses = {model.Visit.class})
@EnableScheduling

public class VisitApplication {

	public static void main(String[] args) {
		SpringApplication.run(VisitApplication.class, args);
	}

}
