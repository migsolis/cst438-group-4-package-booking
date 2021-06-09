package com.cst438.package_booking.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cst438.package_booking.domain.Flight;
import com.cst438.package_booking.domain.PackageInfo;
import com.cst438.package_booking.domain.SearchDetails;

@Service
public class PackageService {
	
	@Autowired
	CarService carService;
	
	@Autowired
	FlightService flightService;
	
	@Autowired 
	HotelService hotelService;
	
	public PackageService(CarService carService, FlightService flightService, HotelService hotelService) {
		this.carService = carService;
		this.flightService = flightService;
		this.hotelService = hotelService;
	}
	
	public List<PackageInfo> getPackages(SearchDetails searchDetails){
		List<PackageInfo> packages = new ArrayList<PackageInfo>();
		
		return packages;
	}

}
