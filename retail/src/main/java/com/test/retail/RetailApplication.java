package com.test.retail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class RetailApplication {

	public static void main(String[] args) {
		SpringApplication.run(RetailApplication.class, args);
	}

}
