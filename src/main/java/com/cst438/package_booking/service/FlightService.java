package com.cst438.package_booking.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cst438.package_booking.domain.Booking;
import com.cst438.package_booking.domain.Flight;
import com.cst438.package_booking.domain.FlightInfo;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class FlightService {
	private static final Logger log = LoggerFactory.getLogger(FlightService.class);
	private RestTemplate restTemplate;
	private String flightsUrl;
	private ObjectMapper mapper;

	public FlightService(@Value("${flights.url}") final String flightsUrl) {
		this.restTemplate = new RestTemplate();
		this.flightsUrl = flightsUrl;
		this.mapper = new ObjectMapper();
	}
	
	public List<FlightInfo> getFlights(String departureLocation, String arrivalLocation,
			LocalDate departureDate) {
		try {
//			ResponseEntity<JsonNode> response = 
//					restTemplate.getForEntity(
//							flightsUrl + "/flights/" +
//							departureLocation + "/" +
//							arrivalLocation + "/" +
//							departureDate.getMonthValue() + "/" +
//							departureDate.getDayOfMonth() + "/" +
//							departureDate.getYear(),
//							JsonNode.class);
//			JsonNode json = response.getBody();
			
			

			List<FlightInfo> flights = new ArrayList<FlightInfo>();
			
			LocalDateTime depDateTime = LocalDateTime.of(departureDate, LocalTime.of(6, 30));
			
			FlightInfo flight = new FlightInfo(123, "Airline 1", departureLocation, arrivalLocation, 
					depDateTime,
					 2345.6);
			
			flights.add(flight);

			return flights;
		} catch (Exception e) {
			log.debug(e.getMessage());
			return null;
		}
	}
	
	public boolean createBooking(int userId, FlightInfo flight, Booking bk) {
		log.info("FlightService: creating booking");
		try {
//			User user = 
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			
			Map<String, Object> reservationMap = new HashMap<>();
			reservationMap.put("flightId", 7);
			reservationMap.put("passengers", bk.getTravelers());
			reservationMap.put("userEmail", bk.getUser().getEmail());
			
			HttpEntity<Map<String, Object>> entity = new HttpEntity<>(reservationMap, headers);
			
			ResponseEntity<JsonNode> response = 
			        restTemplate.postForEntity(flightsUrl + "/reservations",
			                entity, JsonNode.class);
			
			if(response.getStatusCode() != HttpStatus.OK) return false;
			
			JsonNode json = response.getBody();
			
			log.info("FlightService: response - " + response.toString());
			return true;
		} catch(Exception e) {
			log.debug("FlightService: booking failed");
			return false;
		}
	}
	
	public boolean cancelBooking(int bookingId) {
		try {
			log.info("FightService: cancelling booking");
			restTemplate.delete(flightsUrl + "/reservations/" + bookingId);
			log.info("FightService: cancelling booking");
			return true;
		} catch(Exception e) {
			log.debug("FlightService: cancellation failed..." + e.getMessage());
			return false;
		}
	}
}
