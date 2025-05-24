package com.neighbourly.neighbourhoodservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = {
		"com.neighbourly.neighbourhoodservice",
		"com.neighbourly.commonservice"
})
public class NeighbourhoodApplication {

	public static void main(String[] args) {
		SpringApplication.run(NeighbourhoodApplication.class, args);
	}

}
