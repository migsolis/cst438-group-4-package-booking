package com.cst438.package_booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cst438.package_booking.domain.Car;

public interface CarRepository extends JpaRepository<Car, Long> {
	
	Car findById(int id);
	
	Car findByBookingId(int bookingId);

}
