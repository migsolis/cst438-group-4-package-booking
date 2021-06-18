package com.cst438.package_booking;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.cst438.package_booking.domain.Booking;
import com.cst438.package_booking.domain.Car;
import com.cst438.package_booking.service.CarService;

class CarServiceTest {
	
	
	private CarService carService;
	

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void test() {
//		carService = new CarService("https://maha20-car-rental.herokuapp.com/api");
//		CarService spyCarService = Mockito.spy(carService);
//		Car c = new Car();
//		Booking bk = new Booking();
//		bk.setDestination("Las Vegas");
//		bk.setDepartureDate(LocalDateTime.of(2021,6,17,0,0,0));
//		bk.setReturnDate(LocalDateTime.of(2021,6,17,0,0,0));
//
//		boolean validRentalId = carService.createBooking(1, c, bk);
//		boolean cancellationSuccessful = carService.cancelBooking(123);
//		
//		assertTrue(validRentalId);
//		assertTrue(cancellationSuccessful);
	}

}
