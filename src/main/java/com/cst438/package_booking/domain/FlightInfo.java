package com.cst438.package_booking.domain;

import java.time.LocalDateTime;

public class FlightInfo {
	private int id;
	private String airline;
	private String departureLocation;
	private String arrivalLocation;
	private LocalDateTime departureDate;
	private LocalDateTime arrivalDate;

	private double price;
	
	public FlightInfo() {
		
	}
	
	public FlightInfo(int id, String airline, String departureLocation, String arrivalLocation,
			LocalDateTime departureDate, double price) {
		super();
		this.id = id;
		this.airline = airline;
		this.departureLocation = departureLocation;
		this.arrivalLocation = arrivalLocation;
		this.departureDate = departureDate;
		this.price = price;
	}

	public FlightInfo(int id, String airline, String departureLocation, String arrivalLocation,
			LocalDateTime departureDate, LocalDateTime arrivalDate, double price) {
		this.id = id;
		this.airline = airline;
		this.departureLocation = departureLocation;
		this.arrivalLocation = arrivalLocation;
		this.departureDate = departureDate;
		this.arrivalDate = arrivalDate;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}

	public String getDepartureLocation() {
		return departureLocation;
	}

	public void setDepartureLocation(String departureLocation) {
		this.departureLocation = departureLocation;
	}

	public String getArrivalLocation() {
		return arrivalLocation;
	}

	public void setArrivalLocation(String arrivalLocation) {
		this.arrivalLocation = arrivalLocation;
	}

	public LocalDateTime getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(LocalDateTime departureDate) {
		this.departureDate = departureDate;
	}

	public LocalDateTime getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(LocalDateTime arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
}
