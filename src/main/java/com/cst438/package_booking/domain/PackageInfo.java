package com.cst438.package_booking.domain;

public class PackageInfo {
	
	private Car car;
	private FlightInfo flightInfo;
	private Hotel hotelInfo;
	
	public PackageInfo() {
		
	}

	public PackageInfo(Car car, FlightInfo flightInfo, Hotel hotelInfo) {
		super();
		this.car = car;
		this.flightInfo = flightInfo;
		this.hotelInfo = hotelInfo;
	}

	public PackageInfo(FlightInfo flightInfo, Hotel hotelInfo) {
		super();
		this.flightInfo = flightInfo;
		this.hotelInfo = hotelInfo;
		this.car = null;
	}
	
	public PackageInfo(Car car, Hotel hotelInfo) {
		super();
		this.car = car;
		this.flightInfo = null;
		this.hotelInfo = hotelInfo;
	}

	public Car getCarInfo() {
		return car;
	}

	public void setCarInfo(Car car) {
		this.car = car;
	}

	public FlightInfo getFlightInfo() {
		return flightInfo;
	}

	public void setFlightInfo(FlightInfo flightInfo) {
		this.flightInfo = flightInfo;
	}

	public Hotel getHotelInfo() {
		return hotelInfo;
	}

	public void setHotelInfo(Hotel hotelInfo) {
		this.hotelInfo = hotelInfo;
	}

}
