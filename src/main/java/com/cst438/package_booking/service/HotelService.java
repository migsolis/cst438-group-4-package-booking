package com.cst438.package_booking.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cst438.package_booking.domain.Hotel;
import com.cst438.package_booking.domain.Room;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class HotelService {
	private static final Logger log = LoggerFactory.getLogger(HotelService.class);
	private RestTemplate restTemplate;
	private String hotelUrl;
	
	public HotelService(@Value("${hotels.url}") final String hotelUrl) {
		this.restTemplate = new RestTemplate();
		this.hotelUrl = hotelUrl;
	}
	
	public List<Hotel> getHotels(String destination, LocalDate checkinDate, LocalDate checkoutDate){

		Map<Integer,Hotel> map = new HashMap<Integer,Hotel>();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

		try {
			destination = "Las Vegas";
			checkinDate = LocalDate.of(2021,6,1);
			checkoutDate = LocalDate.of(2021,6,7);
			
			ResponseEntity<JsonNode> response = restTemplate.getForEntity(hotelUrl + 
					"/getRooms/?city="+ destination +
					"&checkInDate=" + checkinDate.format(formatter) + 
					"&checkOutDate=" + checkoutDate.format(formatter), JsonNode.class);
			
			 JsonNode json = response.getBody();
			 log.info("REST response " + json.toString());
			 
			 ObjectMapper mapper = new ObjectMapper();
			
			 for(int i = 0; i < json.size(); i++) {
				 log.info(String.valueOf(i));
				 
				 
				 int hotelId = json.get(i).get("hotel").get("id").asInt();
				 JsonNode jHotel = json.get(i).get("hotel");
				 JsonNode jRoom = json.get(i).get("room");
				 
				 if(map.get(hotelId) == null) {
					 Hotel h = mapper.readValue(jHotel.toString(), Hotel.class);
					 map.put(hotelId, h);
				 }
				 
				 Hotel tempH= map.get(jHotel.get("id").asInt());
				 
				 if(tempH.getRooms() == null) {
					 List<Room> r = new ArrayList<Room>();
					 tempH.setRooms(r);
				 }
				 
				 List<Room> rooms = tempH.getRooms();
				 Room room = mapper.readValue(jRoom.toString(), Room.class);
				 rooms.add(room);
				 tempH.setRooms(rooms);
			 }
			 
			List<Hotel> hotels = new ArrayList<Hotel>(map.values());
			log.info("Returning hotels: " + String.valueOf(hotels.size()));
			return hotels;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
		
	}
	
	public void createCustomer() {
		
	}

}
