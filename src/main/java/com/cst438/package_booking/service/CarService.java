package com.cst438.package_booking.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cst438.package_booking.domain.CarInfo;

@Service
public class CarService {
	
	private RestTemplate restTemplate;
	private String carUrl;
	
	public CarService(@Value("${cars.url}") final String carUrl) {
		this.restTemplate = new RestTemplate();
		this.carUrl = carUrl;
	}
	
	public List<CarInfo> getCars(String destination){
		List<CarInfo> cars = new ArrayList<CarInfo>();
		
		return cars;
	}

}
