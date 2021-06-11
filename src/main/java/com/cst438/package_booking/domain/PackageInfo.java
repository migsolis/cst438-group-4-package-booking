package com.cst438.package_booking.domain;

import java.time.LocalDate;

public class PackageInfo {
	private LocalDate departureDate;
	private LocalDate returnDate;
	private Car car;
	private FlightInfo flightInfo;
	private Hotel hotel;
	
	public PackageInfo() {
		
	}

	public PackageInfo(Car car, FlightInfo flightInfo, Hotel hotel) {
		super();
		this.car = car;
		this.flightInfo = flightInfo;
		this.hotel = hotel;
	}

	public PackageInfo(FlightInfo flightInfo, Hotel hotel) {
		super();
		this.flightInfo = flightInfo;
		this.hotel = hotel;
		this.car = null;
	}
	
	public PackageInfo(Car car, Hotel hotel) {
		super();
		this.car = car;
		this.flightInfo = null;
		this.hotel = hotel;
	}

	public LocalDate getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(LocalDate departureDate) {
		this.departureDate = departureDate;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public FlightInfo getFlightInfo() {
		return flightInfo;
	}

	public void setFlightInfo(FlightInfo flightInfo) {
		this.flightInfo = flightInfo;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

}
