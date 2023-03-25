package com.eatza.orderingservice.configuration;


import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;

import com.eatza.order.config.RestTemplateClient;

@ContextConfiguration(classes=RestTemplateClient.class)
public class RestTemplateConfigurationTest {
	
	@Autowired
	RestTemplate restTemplate;
	
	@Test
	public void restTemplateTest() {
		assertNotNull(restTemplate);
	}

}
