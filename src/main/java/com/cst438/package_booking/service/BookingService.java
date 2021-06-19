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
import com.cst438.package_booking.domain.User;
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
	public Booking createBooking(User u, SearchDetails sd, PackageInfo pk) {
		
		Booking b = bookingMapper(u, sd, pk);
//		b.setStatus("In Progress");
		Booking savedBooking;
		
		try {
			savedBooking = bookingRepository.save(b);
			log.info(savedBooking.toString());
		} catch(Exception e) {
			log.debug(e.getMessage());
			
			return null;
		}
		
		boolean bookingsSuccessful = bookRemoteServices(pk, savedBooking);
		
		
		if(!bookingsSuccessful) {
//			cancelBooking(savedBooking.getUserId(), savedBooking.getId());
			savedBooking.setStatus("Failed");
			bookingRepository.save(savedBooking);
			return null;
		}
		savedBooking.setStatus("Complete");
		bookingRepository.save(savedBooking);
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
			carService.cancelBooking(bk.getRentalId());
		}
		
		if((bk.getPackageType() == 1)||(bk.getPackageType() == 2)) {
			flightService.cancelBooking(bk.getFlightId());
		}
		
		hotelService.cancelBooking(bookingId);
		
		bk.setStatus("Cancelled");
		bookingRepository.save(bk);
		
		return true;
	}
	
	// Helper methods
	
	// Maps trip information to a booking object
	public Booking bookingMapper(User u, SearchDetails sd, PackageInfo pk) {
		String carInfo = pk.getCar().getRentalCompany() + ", " + pk.getCar().getCarClass();
		
		int seats = sd.getTravelers();
		String flightInfo = pk.getFlightInfo().getAirline() + ", " + seats + " seats";
		
		Room r = pk.getHotel().getRooms().get(0);
		int nights = (int) ChronoUnit.DAYS.between(pk.getFlightInfo().getDepartureDate(), sd.getReturnDate().atTime(0,0));
		String hotelInfo = pk.getHotel().getName() + ", " +nights + " nights, " +r.getNumberOfBeds() + " " + r.getBedType();
		
		Booking b = new Booking();
//		b.setUserId(userId);
		b.setUser(u);
		b.setPackageType(sd.getPackageType());
		b.setDestination(sd.getDestinationLocation());
		b.setCarInfo(carInfo);
		b.setFlightInfo(flightInfo);
		b.setHotelInfo(hotelInfo);
		b.setTravelers(sd.getTravelers());
		b.setDepartureDate(pk.getFlightInfo().getDepartureDate());
		b.setReturnDate(sd.getReturnDate().atTime(0, 0));
		b.setTransactionDate(LocalDateTime.now());
		
		return b;
	}
	
	// Books reservations on remote services
	boolean bookRemoteServices(PackageInfo pk, Booking b) {
		int userId = b.getUser().getId();
		boolean carIsBooked = false;
		boolean carIsIncluded = (b.getPackageType() == 1)||(b.getPackageType() == 3);
		boolean flightIsBooked = false;
		boolean flightIsIncluded = (b.getPackageType() == 1)||(b.getPackageType() == 2);
		boolean hotelIsBooked = false;
		
		if(carIsIncluded) {
			//Books a car from external service
			int rentalId = carService.createBooking(userId, pk.getCar(), b);
			if(rentalId > 0) {
				carIsBooked = true;
				b.setRentalId(rentalId);
			}
		}
		
		if(flightIsIncluded) {
			//Books a flight from external service
			int flightId = flightService.createBooking(userId, pk.getFlightInfo(), b);
			if(flightId > 0) {
				flightIsBooked = true;
				b.setFlightId(flightId);
			}
		}
		
		//Books a room from external service
		hotelIsBooked = hotelService.createBooking(userId,pk.getHotel(), b);
		
		bookingRepository.save(b);
		
		return (carIsIncluded ? carIsBooked : true)&&(flightIsIncluded ? flightIsBooked : true)&&(hotelIsBooked);
	}
	
}
