package com.cst438.package_booking.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
	
	List<Booking> findByConfirmation(String confirmation);
	
	List<Booking> findByUserId(int userId);
}
