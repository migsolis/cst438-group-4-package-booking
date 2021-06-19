package com.cst438.package_booking;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.cst438.package_booking.domain.Booking;
import com.cst438.package_booking.domain.Car;
import com.cst438.package_booking.domain.User;
import com.cst438.package_booking.service.CarService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

class CarServiceTest {
	
	@Mock
	private RestTemplate mockRestTemplate;
	
	@InjectMocks
	private CarService carService = new CarService("");
	

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testService() {
		
		Car c = new Car("RentalCom1", "Luxury Sports Car", 1234.0);
		User u = new User();
		u.setId(111);
		u.setEmail("test@test");
		Booking b = new Booking(123, 3, "City2", 321);
		b.setDepartureDate(LocalDateTime.of(2021,6,18,0,0,0));
		b.setReturnDate(LocalDateTime.of(2021,6,20,0,0,0));
		b.setUser(u);
		
		String json = "{\"reservation_id\":456}";
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode;
		
		try {
			jsonNode = mapper.readTree(json);
			Mockito.when(mockRestTemplate.getForEntity(anyString(),any()))
					.thenReturn(new ResponseEntity(jsonNode, HttpStatus.OK));
			doNothing().when(mockRestTemplate).delete(anyString());
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		List<Car> carList = carService.getCars("City1");
		int rentalId = carService.createBooking(u.getId(), c, b);
		boolean cancellationComplete = carService.cancelBooking(456);
		
		assertTrue(carList.size() > 0);
		assertEquals(456, rentalId);
		assertTrue(cancellationComplete);
	}

}
