package com.example.mspl_connect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MsplConnectApplication {
	public static void main(String[] args) {
		SpringApplication.run(MsplConnectApplication.class, args);
	}	
}