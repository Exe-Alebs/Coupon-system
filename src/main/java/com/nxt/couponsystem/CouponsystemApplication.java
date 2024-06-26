package com.nxt.couponsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication()
@EnableSpringDataWebSupport
@EntityScan
public class CouponsystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(CouponsystemApplication.class, args);
	}

}
