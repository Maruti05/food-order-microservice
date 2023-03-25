package com.eatza.orderingservice.configuration;



import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.eatza.order.config.SwaggerConfiguration;


@ContextConfiguration(classes=SwaggerConfiguration.class)
public class SwaggerDocketConfigurationTest {
	
//	@Autowired
//	Docket docket;
//	
//	
//	@Autowired
//	UiConfiguration uiconfiguration;
//	
//	@Test
//	public void docketTest() {
//		assertNotNull(docket);
//	}
//	
//	@Test
//	public void uiconfigurationTest() {
//		assertNotNull(uiconfiguration);
//	}

}
