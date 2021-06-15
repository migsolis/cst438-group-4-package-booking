package com.cst438.package_booking;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cst438.package_booking.domain.Booking;
import com.cst438.package_booking.repository.BookingRepository;
import com.cst438.package_booking.service.BookingService;
import com.cst438.package_booking.service.CarService;
import com.cst438.package_booking.service.FlightService;
import com.cst438.package_booking.service.HotelService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@SpringBootTest
class BookingServiceTest {
	
	@MockBean
	private BookingRepository mockBookingRepository;
	
	@MockBean
	private CarService mockCarService;
	
	@MockBean
	private FlightService mockFlightService;
	
	@MockBean
	private HotelService mockHotelService;
	
	private BookingService bookingService;
	
	@BeforeEach
	public void setupEach() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	void getBookingForUser_userId_bookingList() {
		List<Booking> testBookings = new ArrayList<Booking>();
		Booking testBooking = new Booking();
		
		testBooking.setId(1);
		testBooking.setPackageType(3);
		testBooking.setDestination("Las Vegas");
		testBooking.setUserId(321);
		
		testBookings.add(testBooking);
		given(mockBookingRepository.findByUserId(321)).willReturn(testBookings);
		
		bookingService = new BookingService(mockBookingRepository, mockCarService, mockFlightService, mockHotelService);
		
		List<Booking> userBookings = bookingService.getBookingsForUser(1);
		assertEquals(testBooking, testBookings.get(0));
	}

}
