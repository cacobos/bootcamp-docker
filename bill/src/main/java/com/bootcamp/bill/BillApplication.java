package com.bootcamp.bill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableEurekaClient
@Import(model.Bill.class)
@EntityScan(basePackageClasses = {model.Bill.class})
@EnableScheduling
public class BillApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillApplication.class, args);
	}

}
