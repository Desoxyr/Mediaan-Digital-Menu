package com.example.tableservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TableServiceApplication {

	ObjectMapper mapper = new ObjectMapper();
	public static void main(String[] args) {

		SpringApplication.run(TableServiceApplication.class, args);
	}

}
