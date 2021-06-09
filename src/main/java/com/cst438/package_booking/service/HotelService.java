package com.cst438.package_booking.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cst438.package_booking.domain.HotelInfo;
import com.cst438.package_booking.domain.RoomInfo;

@Service
public class HotelService {
	private RestTemplate restTemplate;
	private String hotelUrl;
	
	public HotelService(@Value("${hotels.url}") final String hotelUrl) {
		this.restTemplate = new RestTemplate();
		this.hotelUrl = hotelUrl;
	}
	
	public List<HotelInfo> getHotels(String destination, LocalDate arrivalDate, int nightsCount){
		List<HotelInfo> hotels = new ArrayList<HotelInfo>();
		
		return hotels;
	}
	
	public List<RoomInfo> getRooms(int hotelId, LocalDate arrivalDate, int nightsCount){
		List<RoomInfo> rooms = new ArrayList<RoomInfo>();
		
		return rooms;
	}

}
