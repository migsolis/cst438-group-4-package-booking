package com.cst438.package_booking.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
public class Booking {
	@Id
	@GeneratedValue
	private int bookingId;
	private int userId;
	private int adults;
	private int children;
	private double totalPrice;
	private int status;
	@DateTimeFormat(iso=ISO.DATE_TIME)
	private LocalDateTime departureDate;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private LocalDateTime returnDate;
	private LocalDateTime transactionDate;
	private String confirmation;
	private int flightId;
	private int roomId;
	private int carId;
	
	public Booking() {
		
	}

	public int getBookingId() {
		return bookingId;
	}

	public Booking(int userId, int adults, int children, double totalPrice, LocalDateTime departureDate,
			LocalDateTime returnDate) {
		super();
		this.userId = userId;
		this.adults = adults;
		this.children = children;
		this.totalPrice = totalPrice;
		this.departureDate = departureDate;
		this.returnDate = returnDate;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getAdults() {
		return adults;
	}

	public void setAdults(int adults) {
		this.adults = adults;
	}

	public int getChildren() {
		return children;
	}

	public void setChildren(int children) {
		this.children = children;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public LocalDateTime getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(LocalDateTime departureDate) {
		this.departureDate = departureDate;
	}

	public LocalDateTime getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDateTime returnDate) {
		this.returnDate = returnDate;
	}

	public LocalDateTime getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDateTime transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getConfirmation() {
		return confirmation;
	}

	public void setConfirmation(String confirmation) {
		this.confirmation = confirmation;
	}

	public int getFlightId() {
		return flightId;
	}

	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public int getCarId() {
		return carId;
	}

	public void setCarId(int carId) {
		this.carId = carId;
	}


	
}
