package com.infogain.retailer;

import com.infogain.retailer.repository.RetailerSecurityUserServiceRepository;
import com.infogain.retailer.entity.SecurityUser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RetailerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RetailerApplication.class, args);
	}

}
