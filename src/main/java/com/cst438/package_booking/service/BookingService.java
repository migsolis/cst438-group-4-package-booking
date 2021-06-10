package com.cst438.package_booking.service;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cst438.package_booking.domain.Booking;
import com.cst438.package_booking.domain.BookingRepository;
import com.cst438.package_booking.domain.Car;
import com.cst438.package_booking.domain.FlightInfo;
import com.cst438.package_booking.domain.Hotel;
import com.cst438.package_booking.domain.PackageInfo;

@Service
public class BookingService {
	private static final Logger log = LoggerFactory.getLogger(BookingService.class);

	@Autowired
	BookingRepository bookingRepository;
	
	public BookingService(BookingRepository br) {
		this.bookingRepository = br;
	}
	
	public String createBooking(Booking booking, int carId, int flightId, int hotelId) {
		
		booking.setTransactionDate(LocalDateTime.now());
		
		try {
			Booking savedBooking = bookingRepository.save(booking);
			
			log.info(savedBooking.toString());
			
			String confirmation = String.valueOf(savedBooking.hashCode());
			
			return confirmation;
			
		} catch(Exception e) {
			log.debug(e.getMessage());
			
			return null;
		}

	}
	
	public List<Booking> getBookingsForUser(int userId){
		List<Booking> bookings;
		
		try {
			bookings = bookingRepository.findByUserId(userId);
			log.info("Booking info return " + bookings.size() + " items.");

			return bookings;
		} catch(Exception e) {
			log.debug(e.getMessage());
			
			return null;
		}
		
	}
}
