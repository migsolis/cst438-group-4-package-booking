package com.cst438.package_booking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cst438.package_booking.domain.PackagesRepository;
import com.cst438.package_booking.domain.BookingInfo;
import com.cst438.package_booking.domain.PackageInfo;

@Service
public class BookingService {

	@Autowired
	PackagesRepository packageRepository;
	
	public BookingService(PackagesRepository pr) {
		this.packageRepository = pr;
	}
	
	public String createBooking(int userId, PackageInfo packageInfo) {
		String confirmation = "";
		
		return confirmation;
	}
	
	public List<BookingInfo> getBookingsForUser(int userId){
		List<BookingInfo> bookings = packageRepository.findByUserId(userId);
		
		
		
		return bookings;
	}
}
