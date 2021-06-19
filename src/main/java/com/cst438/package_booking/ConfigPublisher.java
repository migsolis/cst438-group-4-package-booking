package com.cst438.package_booking;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigPublisher {

	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
}
