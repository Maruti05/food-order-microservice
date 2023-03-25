package com.eatza.apigateway;

import java.util.Objects;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@EnableHystrix
@EnableDiscoveryClient
@SpringBootApplication
public class SpringCloudApiGatewayApplication {
	private static final Logger logger = LoggerFactory.getLogger(SpringCloudApiGatewayApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(SpringCloudApiGatewayApplication.class, args);
	}
	@Autowired
	private GatewayProperties props;
	@PostConstruct
	public void init() {
		logger.info(Objects.toString(props));
	}
}
