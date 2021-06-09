package com.cst438.package_booking.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.asm.TypeReference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cst438.package_booking.domain.Flight;

@Service
public class FlightService {
	private static final Logger log = LoggerFactory.getLogger(FlightService.class);
	private RestTemplate restTemplate;
	private String flightsUrl;

	public FlightService(@Value("${flights.url}") final String flightsUrl) {
		this.restTemplate = new RestTemplate();
		this.flightsUrl = flightsUrl;
	}
	
	
	//return all flights
	public List<Flight> getAllFlights() {
		try {
			ResponseEntity<Flight[]> response = 
					restTemplate.getForEntity(
							flightsUrl, 
							Flight[].class);
			
			List<Flight> flights = Arrays.asList(response.getBody());

			return flights;
		} catch (Exception e) {
			log.debug(e.getMessage());
			return null;
		}
	}
	
	//return flights based on dates, locations
	public List<Flight> getFlights(String departureLocation, String arrivalLocation,
			String departureDate, String arrivalDate) {
		try {
			ResponseEntity<Flight[]> response = 
					restTemplate.getForEntity(
							flightsUrl + "/?" +
							"&dl=" + departureLocation +
							"&al=" + arrivalLocation +
							"&dd=" + departureDate +
							"&ad=" + arrivalDate,
							Flight[].class);
			
			List<Flight> flights = Arrays.asList(response.getBody());

			return flights;
		} catch (Exception e) {
			log.debug(e.getMessage());
			return null;
		}
	}


}
