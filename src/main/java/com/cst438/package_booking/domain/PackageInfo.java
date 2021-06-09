package com.cst438.package_booking.domain;

public class PackageInfo {
	
	private CarInfo carInfo;
	private FlightInfo flightInfo;
	private HotelInfo hotelInfo;
	
	public PackageInfo() {
		
	}

	public PackageInfo(CarInfo carInfo, FlightInfo flightInfo, HotelInfo hotelInfo) {
		super();
		this.carInfo = carInfo;
		this.flightInfo = flightInfo;
		this.hotelInfo = hotelInfo;
	}

	public PackageInfo(FlightInfo flightInfo, HotelInfo hotelInfo) {
		super();
		this.flightInfo = flightInfo;
		this.hotelInfo = hotelInfo;
		this.carInfo = null;
	}
	
	public PackageInfo(CarInfo carInfo, HotelInfo hotelInfo) {
		super();
		this.carInfo = carInfo;
		this.flightInfo = null;
		this.hotelInfo = hotelInfo;
	}

	public CarInfo getCarInfo() {
		return carInfo;
	}

	public void setCarInfo(CarInfo carInfo) {
		this.carInfo = carInfo;
	}

	public FlightInfo getFlightInfo() {
		return flightInfo;
	}

	public void setFlightInfo(FlightInfo flightInfo) {
		this.flightInfo = flightInfo;
	}

	public HotelInfo getHotelInfo() {
		return hotelInfo;
	}

	public void setHotelInfo(HotelInfo hotelInfo) {
		this.hotelInfo = hotelInfo;
	}

}
