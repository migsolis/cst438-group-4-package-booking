package com.cst438.package_booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.cst438.package_booking.domain.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {
	
	Booking findById(int id);
	
	List<Booking> findByUserId(int userId);
	
	@Transactional
	Booking deleteById(int id);
}
