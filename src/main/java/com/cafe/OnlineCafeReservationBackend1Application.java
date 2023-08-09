package com.cafe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class OnlineCafeReservationBackend1Application {

	public static void main(String[] args) {
		SpringApplication.run(OnlineCafeReservationBackend1Application.class, args);
		System.out.println("Online Cafe Application Started...");
	}

}
