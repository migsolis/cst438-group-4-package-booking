package com.cst438.package_booking;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;

import com.cst438.package_booking.domain.Booking;
import com.cst438.package_booking.domain.FlightInfo;
import com.cst438.package_booking.service.FlightService;

class FlightServiceTest {
	
	private FlightService flightService;
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void test() {
		
	}

}
