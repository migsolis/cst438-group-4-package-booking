package com.cst438.package_booking.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cst438.package_booking.domain.Booking;
import com.cst438.package_booking.domain.Car;
import com.cst438.package_booking.repository.CarRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CarService {
	
	@Autowired
	private CarRepository carRepository;
	
	private static final Logger log = LoggerFactory.getLogger(CarService.class);
	private RestTemplate restTemplate;
	private String carUrl;
	private ObjectMapper mapper;

	
	public CarService(@Value("${cars.url}") final String carUrl) {
		this.restTemplate = new RestTemplate();
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
	
	public boolean createBooking(int userId, Car c, Booking bk) {
		try {
			log.info("CarService: Creating reservation");
			ResponseEntity<JsonNode> response = restTemplate.getForEntity(carUrl +
					"/book?companyid=1" + 
					"&startdate=" + bk.getDepartureDate().toLocalDate()+
					"&enddate=" + bk.getReturnDate().toLocalDate() +
					"&location=" + bk.getDestination(), JsonNode.class);
			
			JsonNode json = response.getBody();
			c.setId(json.get("reservation_id").asInt());
			c.setTotalPrice(json.get("total_price").asInt());
			c.setBookingId(bk.getId());
			carRepository.save(c);
			
			log.info("CarService: Reservation Complete!" + response.getBody().toString());
			return true;
		} catch(Exception e) {
			log.debug("CarService: Booking failed, " + e.getMessage());
			return false;
		}
	}
	
	public boolean cancelBooking(int bookingId) {
		log.info("CarService: cancelling car for bookingId - " + String.valueOf(bookingId));
		try {
			Car c = carRepository.findByBookingId(bookingId);
			if(c == null) return false;
			
			restTemplate.delete(carUrl +
					"/cancel?companyid=1" +
					"&reservationid=" + c.getId());
			log.info("CarService: cancellation complete");
			return true;
		} catch(Exception e) {
			log.debug("CarService: cancellation failed, " + e.getMessage());
			return false;
		}
	}
}
