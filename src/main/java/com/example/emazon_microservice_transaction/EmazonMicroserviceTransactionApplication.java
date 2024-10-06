package com.example.emazon_microservice_transaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EmazonMicroserviceTransactionApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmazonMicroserviceTransactionApplication.class, args);
	}

}
