package com.cst438.package_booking.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cst438.package_booking.domain.Booking;
import com.cst438.package_booking.domain.Car;

@Service
public class CarService {
	
	private RestTemplate restTemplate;
	private String carUrl;
	
	public CarService(@Value("${cars.url}") final String carUrl) {
		this.restTemplate = new RestTemplate();
		this.carUrl = carUrl;
	}
	
	public List<Car> getCars(String destination){
		List<Car> cars = new ArrayList<Car>();
		Car car = new Car("RentalCom1", "Luxury Sports Car", 1234.0);
		cars.add(car);
		return cars;
	}
	
	public boolean createBooking(int userId, Car c, Booking bk) {
		try {
			
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	public boolean cancelBooking(int bookingId) {
		try {
			
			return true;
		} catch(Exception e) {
			return false;
		}
	}
}
