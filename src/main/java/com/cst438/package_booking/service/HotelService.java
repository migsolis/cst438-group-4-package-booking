package com.cst438.package_booking.service;

import java.time.temporal.ChronoUnit;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cst438.package_booking.domain.Booking;
import com.cst438.package_booking.domain.Customer;
import com.cst438.package_booking.domain.Hotel;
import com.cst438.package_booking.domain.PackageBooking;
import com.cst438.package_booking.domain.Room;
import com.cst438.package_booking.domain.User;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class HotelService {
	private static final Logger log = LoggerFactory.getLogger(HotelService.class);
	private RestTemplate restTemplate;
	private String hotelUrl;
	private ObjectMapper mapper;
	
	
	
	public HotelService(@Value("${hotels.url}") final String hotelUrl) {
		this.restTemplate = new RestTemplate();
		this.hotelUrl = hotelUrl;
		this.mapper = new ObjectMapper();
	}
	
	// Gets available hotels and rooms from external hotel service
	public List<Hotel> getHotels(String destination, LocalDate checkinDate, LocalDate checkoutDate){

		Map<Integer,Hotel> map = new HashMap<Integer,Hotel>();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

		try {
			
			ResponseEntity<JsonNode> response = restTemplate.getForEntity(hotelUrl + 
					"/getRooms/?city="+ destination +
					"&checkInDate=" + checkinDate.format(formatter) + 
					"&checkOutDate=" + checkoutDate.format(formatter), JsonNode.class);
			
			 JsonNode json = response.getBody();
			 log.info("REST response " + json.toString());
			 
			 if(response.getStatusCode() == HttpStatus.NOT_FOUND) return null;
			 
			 for(int i = 0; i < json.size(); i++) {
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
	
	//Creates new customer at external hotel service
	public void createCustomer(User u) {
		
		Customer c = u.toCustomer(u);
		
		try {
			ResponseEntity<Customer> response = restTemplate.postForEntity(hotelUrl +
					"/createNewCustomer/", c, Customer.class);
			log.info(response.toString());
		} catch(Exception e) {
			log.info("New customer post exploded..." + e.getMessage());
		}

	}
	
	//Creates a new booking at external hotel service
	public boolean createBooking(int customerId, Hotel hotel, Booking bk) {
		int roomId = hotel.getRooms().get(0).getId();
		double pricePerNight = hotel.getRooms().get(0).getPricePerNight();
		int nights = (int) ChronoUnit.DAYS.between(bk.getDepartureDate(), bk.getReturnDate());
		double totalPrice = pricePerNight * nights;
		
		PackageBooking pk = new PackageBooking();
		pk.setId(bk.getId());
		pk.setRoom_id(roomId);
		pk.setCustomer_id(customerId);
		pk.setTotal_price(totalPrice);
		pk.setCheck_in_date(Date.valueOf(bk.getDepartureDate().toLocalDate()));
		pk.setCheck_out_date(Date.valueOf(bk.getReturnDate().toLocalDate()));
		pk.setNumber_occupants(bk.getTravelers());
		
		try {
			ResponseEntity<PackageBooking> response = restTemplate.postForEntity(hotelUrl +
					"/newPackageBooking/", 
					pk, PackageBooking.class);
			log.info(response.toString());
			return true;
		} catch(Exception e) {
			log.info("New booking post exploded..." + e.getMessage());
			return false;
		}
	}
	
	//Cancels booking at external hotel service
	public boolean cancelBooking(int id) {
		
		try {
			restTemplate.delete(hotelUrl +"/cancelPackageBooking/" + id);
			
			log.info("Cancelled hotel booking..." );
			return true;
		} catch(Exception e) {
			log.info("Canceling hotel booking failed..." + e.getMessage());
			return false;

		}
		
	}

}
