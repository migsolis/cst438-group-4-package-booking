package com.cst438.package_booking.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PackagesRepository extends JpaRepository<BookingInfo, Long> {
	List<BookingInfo> findByConfirmation(String confirmation);
	
	List<BookingInfo> findByUserId(int userId);
}
