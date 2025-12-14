package com.solutionez.restandwebsockets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RestandwebsocketsApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestandwebsocketsApplication.class, args);
	}

}
