package com.cst438.package_booking.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cst438.package_booking.domain.Hotel;
import com.cst438.package_booking.domain.Room;

@Service
public class HotelService {
	private RestTemplate restTemplate;
	private String hotelUrl;
	
	public HotelService(@Value("${hotels.url}") final String hotelUrl) {
		this.restTemplate = new RestTemplate();
		this.hotelUrl = hotelUrl;
	}
	
	public List<Hotel> getHotels(String destination, LocalDate arrivalDate, int nightsCount){
		List<Hotel> hotels = new ArrayList<Hotel>();
		
		return hotels;
	}
	
	public List<Room> getRooms(int hotelId, LocalDate arrivalDate, int nightsCount){
		List<Room> rooms = new ArrayList<Room>();
		
		return rooms;
	}
	
	public List<Hotel> getTestHotels(String destination, LocalDate arrivalDate, int nightsCount){
		List<Hotel> hotels = new ArrayList<Hotel>();
		Hotel hotel;
		
		for(int i = 0; i < 3; i++) {
			hotel= new Hotel(123, "Hotel " + i, LocalDate.of(2021, 6, 8), LocalDate.of(2021, 6, 10));
			
			hotels.add(hotel);
		}
		
		
		return hotels;
	}

}
