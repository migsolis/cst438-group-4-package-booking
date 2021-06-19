package com.cst438.package_booking.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cst438.package_booking.domain.Booking;
import com.cst438.package_booking.domain.Car;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CarService {
	@Autowired
	private RestTemplate restTemplate;
	
	private static final Logger log = LoggerFactory.getLogger(CarService.class);
	private String carUrl;
	private ObjectMapper mapper;
	
	public CarService(@Value("${cars.url}") final String carUrl) {
//		this.restTemplate = new RestTemplate();
		this.carUrl = carUrl;
		this.mapper = new ObjectMapper();
	}
	
	public List<Car> getCars(String destination){
		List<Car> cars = new ArrayList<Car>();
		Car car = new Car("Rental Company 1", "Standard", 51);
		car.setDescription("Volkswagen Jetta, Toyota Corolla or similar.");
		cars.add(car);
		return cars;
	}
	
	public int createBooking(int userId, Car c, Booking bk) {
		try {
			log.info("CarService: Creating reservation");
			ResponseEntity<JsonNode> response = restTemplate.getForEntity(carUrl +
					"/book?companyid=1" + 
					"&startdate=" + bk.getDepartureDate().toLocalDate()+
					"&enddate=" + bk.getReturnDate().toLocalDate() +
					"&location=" + bk.getDestination(), JsonNode.class);
			
			JsonNode json = response.getBody();
			
			log.info("CarService: Reservation Complete!" + response.getBody().toString());
			
			if(response.getStatusCode() != HttpStatus.OK) return -1;
			return json.get("reservation_id").asInt();
		} catch(Exception e) {
			log.debug("CarService: Booking failed, " + e.getMessage());
			return -1;
		}
	}
	
	public boolean cancelBooking(int bookingId) {
		log.info("CarService: cancelling car for bookingId - " + String.valueOf(bookingId));
		try {
			restTemplate.delete(carUrl +
					"/cancel?companyid=1" +
					"&reservationid=" + bookingId);
			log.info("CarService: cancellation complete");
			return true;
		} catch(Exception e) {
			log.debug("CarService: cancellation failed, " + e.getMessage());
			return false;
		}
	}
}
