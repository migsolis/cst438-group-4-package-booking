package com.cst438.package_booking;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.cst438.package_booking.domain.Booking;
import com.cst438.package_booking.domain.FlightInfo;
import com.cst438.package_booking.domain.User;
import com.cst438.package_booking.service.FlightService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class FlightServiceTest {
	
	@Mock
	private RestTemplate mockRestTemplate;
	
	@InjectMocks
	private FlightService flightService = new FlightService("");
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void flightService() {
		
		List<FlightInfo> flights = flightService.getFlights("City1",
				"City2", LocalDate.of(2021, 6, 18));
		
		assertTrue(flights.size() > 0);

		FlightInfo f = new FlightInfo(123, "Airline1", "City1", "City2", LocalDateTime.of(2021, 6, 6, 5, 30), 123.4);
		User u = new User();
		u.setEmail("test@test");
		Booking b = new Booking(123, 3, "City2", 321);
		b.setUser(u);
		
		String json = "{ \"reservationId\": 456}";
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode;
		try {
			jsonNode = mapper.readTree(json);
			when(mockRestTemplate.postForEntity(anyString(), any(), any()))
					.thenReturn(new ResponseEntity(jsonNode, HttpStatus.OK));
			doNothing().when(mockRestTemplate).delete(anyString());
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		int flightBookingId = flightService.createBooking(321, f, b);
		boolean cancellationComplete = flightService.cancelBooking(456);
		
		assertEquals(456, flightBookingId);
		assertTrue(cancellationComplete);
		
	}
	
}