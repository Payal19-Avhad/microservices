package com.ass;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class NaminServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(NaminServerApplication.class, args);
	}

}
