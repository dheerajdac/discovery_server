package com.dheerajdac.discovery_server;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;



@EnableEurekaServer
@SpringBootApplication
public class DiscoveryServerApplication {

	@Value("${log}")
	private static String log;

	public static void main(String[] args) {
		System.out.println(log);
		SpringApplication.run(DiscoveryServerApplication.class, args);
	}

}
