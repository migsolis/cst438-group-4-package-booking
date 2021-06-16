package com.cst438.package_booking.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cst438.package_booking.domain.Booking;
import com.cst438.package_booking.domain.PackageInfo;
import com.cst438.package_booking.domain.Room;
import com.cst438.package_booking.domain.SearchDetails;
import com.cst438.package_booking.repository.BookingRepository;

@Service
public class BookingService {
	private static final Logger log = LoggerFactory.getLogger(BookingService.class);

	@Autowired
	BookingRepository bookingRepository;
	
	@Autowired
	CarService carService;
	
	@Autowired
	FlightService flightService;
	
	@Autowired
	HotelService hotelService;
	
	public BookingService () {};
	
	public BookingService(BookingRepository br, CarService cs, FlightService fs, HotelService hs) {
		this.bookingRepository = br;
		this.carService = cs;
		this.flightService = fs;
		this.hotelService = hs;
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
	
	//Creates new booking and returns the booking id
	public Booking createBooking(int userId, SearchDetails sd, PackageInfo pk) {
		Booking b = bookingMapper(userId, sd, pk);
		Booking savedBooking;
		
		try {
			savedBooking = bookingRepository.save(b);
			log.info(savedBooking.toString());
		} catch(Exception e) {
			log.debug(e.getMessage());
			
			return null;
		}
		
		boolean bookingsSuccessful = bookRemoteServices(userId, pk, b);
		
		
		if(!bookingsSuccessful) {
			cancelBooking(savedBooking.getUserId(), savedBooking.getId());
			bookingRepository.deleteById(savedBooking.getId());
			return null;
		}
		
		return savedBooking;

	}
	
	//Cancels existing bookings
	public boolean cancelBooking(int userId, int bookingId) {
		log.info("Cancelling Booking " + String.valueOf(bookingId));
		Booking bk;
		
		try {
			bk = bookingRepository.findById(bookingId);
		} catch(Exception e) {
			log.debug(e.getMessage());
			return false;
		}
		
		if((bk.getPackageType() == 1)||(bk.getPackageType() == 3)) {
			carService.cancelBooking(bookingId);
		}
		
		if((bk.getPackageType() == 1)||(bk.getPackageType() == 2)) {
			flightService.cancelBooking(bookingId);
		}
		
		hotelService.cancelBooking(bookingId);
		
		bk.setStatus("Cancelled");
		bookingRepository.save(bk);
		
		return true;
	}
	
	// Helper methods
	
	// Maps trip information to a booking object
	public Booking bookingMapper(int userId, SearchDetails sd, PackageInfo pk) {
		String carInfo = pk.getCar().getRentalCompany() + ", " + pk.getCar().getCarClass();
		
		int seats = sd.getTravelers();
		String flightInfo = pk.getFlightInfo().getAirline() + ", " + seats + " seats";
		
		Room r = pk.getHotel().getRooms().get(0);
		int nights = (int) ChronoUnit.DAYS.between(pk.getFlightInfo().getDepartureDate(), sd.getReturnDate().atTime(0,0));
		String hotelInfo = pk.getHotel().getName() + ", " +nights + " nights, " +r.getNumberOfBeds() + " " + r.getBedType();
		
		Booking b = new Booking();
		b.setUserId(userId);
		b.setPackageType(sd.getPackageType());
		b.setDestination(sd.getDestinationLocation());
		b.setCarInfo(carInfo);
		b.setFlightInfo(flightInfo);
		b.setHotelInfo(hotelInfo);
		b.setTravellers(sd.getTravelers());
		b.setDepartureDate(pk.getFlightInfo().getDepartureDate());
		b.setReturnDate(sd.getReturnDate().atTime(0, 0));
		b.setTransactionDate(LocalDateTime.now());
		
		return b;
	}
	
	// Books reservations on remote services
	boolean bookRemoteServices(int userId, PackageInfo pk, Booking b) {
		boolean carIsBooked = false;
		boolean carIsIncluded = (b.getPackageType() == 1)||(b.getPackageType() == 3);
		boolean flightIsBooked = false;
		boolean flightIsIncluded = (b.getPackageType() == 1)||(b.getPackageType() == 2);
		boolean hotelIsBooked = false;
		
		if(carIsIncluded) {
			//Books a car from external service
			carIsBooked = carService.createBooking(userId, pk.getCar(), b);
		}
		
		if(flightIsIncluded) {
			//Books a flight from external service
			flightIsBooked = flightService.createBooking(userId, pk.getFlightInfo(), b);
		}
		
		//Books a room from external service
		hotelIsBooked = hotelService.createBooking(userId,pk.getHotel(), b);
		
		return (carIsIncluded ? carIsBooked : true)&&(flightIsIncluded ? flightIsBooked : true)&&(hotelIsBooked);
	}
	
}
