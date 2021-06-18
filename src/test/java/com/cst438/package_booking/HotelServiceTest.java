package com.cst438.package_booking;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.*;

import java.net.URI;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.cst438.package_booking.service.HotelService;
import com.fasterxml.jackson.databind.JsonNode;

class HotelServiceTest {
	
	@MockBean
	private RestTemplate mockRestTemplate;
	
	private HotelService hotelService;

	@BeforeEach
	void setUp() throws Exception {
		
	}

	@Test
	void hotelService_validDetails_returnHotelsList() {
//		hotelService = new HotelService( "Test", mockRestTemplate);
		
//		JsonNode json = ;
		
//		Mockito.when(mockRestTemplate.getForEntity(any(URI.class), any())).thenReturn(new ResponseEntity(json, HttpStatus.ACCEPTED));
	}

}
