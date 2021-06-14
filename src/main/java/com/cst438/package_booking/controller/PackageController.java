package com.cst438.package_booking.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cst438.package_booking.domain.Booking;
import com.cst438.package_booking.domain.Flight;
import com.cst438.package_booking.domain.Hotel;
import com.cst438.package_booking.domain.PackageInfo;
import com.cst438.package_booking.domain.SearchDetails;
import com.cst438.package_booking.domain.SearchDetailsRepository;
import com.cst438.package_booking.domain.User;
import com.cst438.package_booking.service.BookingService;
import com.cst438.package_booking.service.FlightService;
import com.cst438.package_booking.service.HotelService;
import com.cst438.package_booking.service.PackageService;


@Controller
public class PackageController {
	private static final Logger log = LoggerFactory.getLogger(PackageController.class);
	
	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private FlightService flightService;
	
	@Autowired
	private HotelService hotelService;
	
	@Autowired
	PackageService packageService;
	
	@Autowired
	SearchDetailsRepository searchRepository;
	
	@GetMapping("/")
	public String packageHome(Model model) {
		
		return "index";
	}
	
	@GetMapping("/flights")
	public String getFlights(Model model) {
		
		List<Flight> flights = flightService.getAllFlights();
		model.addAttribute("flights", flights);
		return "flight_list";
		
//		ResponseEntity<List<Flight>> responseEntity = flightService.getAllFlights();
////		List<Flight> flights = flightService.getAllFlights().getBody();
////		model.addAttribute("flights", flights);
//		if (responseEntity != null) {
//			HttpStatus httpStatus = responseEntity.getStatusCode();
//			
//			System.out.println("Status Code: " + httpStatus);
//			
//			List<Flight> flights = responseEntity.getBody();
//			
//			model.addAttribute("flights", flights);
//			
//			return "flight_list";
//			
//		} else {
//			System.out.println("Can not connect to find web method");
//			
//			return "index";
//		}	
	}
	
	@GetMapping("/searchflights")
	public String searchFlights(Model model) {
		
		List<Flight> flights = flightService.getFlights("","","","");
		model.addAttribute("flights", flights);
		return "flight_list";
		
	}
	
	// Returns the form for a package search
	@GetMapping("/search/new")
	public String getPackages(Model model) {
		SearchDetails details = new SearchDetails();

		model.addAttribute("searchDetails", details);
		model.addAttribute("now", LocalDate.now());
		
		return "search_form";
	}
	
	@PostMapping("/search/new")
	public String processSearchForm(
			@Valid SearchDetails searchDetails, BindingResult result, Model model) {
		log.info("POST /search/new called!!");
		
		log.info(searchDetails.toString());
		
		if(result.hasErrors()) {
			model.addAttribute("now", searchDetails.getDepartureDate());
			log.debug("Errors detected in search form.");
			return "search_form";
		}
		
		SearchDetails savedSearch = searchRepository.save(searchDetails);
		
		log.info("Searched saved: " + savedSearch.toString());
		
		List<PackageInfo> packages = packageService.getPackages(savedSearch);
		
		log.info("Packages returned...");
		
		model.addAttribute("packages", packages);
		model.addAttribute("searchDetails", savedSearch);
		
		return "search_results";
	}
	
	@GetMapping("/user/bookings")
	public String viewBookings(Model model) {
		List<Booking> bookings = bookingService.getBookingsForUser(1);
		return "index";
	}
	
	@PostMapping("/booking/new")
	public String createNewBooking(
			@ModelAttribute("booking") Booking bk,
			@RequestParam("carId") int carId,
			@RequestParam("flightId") int flightId,
			@RequestParam("hotelId") int hotelId,
			Model model) {
		
		log.info(bk.toString());
		log.info("carId: " + carId + ", flightId: " + flightId + ", hotel: " + hotelId);
		
		String confirmation = bookingService.createBooking(bk, carId, flightId, hotelId);
		log.info("Booking complete, confirmation number " + confirmation);
		return "index";
	}
	
	@GetMapping("/hotels")
	public String testHotels() {
//		List<Hotel> hotels = hotelService.getHotels(null, null, null);
//		log.info("Hotels list size: " + String.valueOf(hotels.size()));
//		hotelService.createCustomer(new User());
//		Booking bk = new Booking(1,2,2,100.0,
//				LocalDateTime.of(2021, 6, 12,0,0,0),
//				LocalDateTime.of(2021, 6, 14,0,0,0));
//		hotelService.createBooking(1, 1, 50.0, bk);
		hotelService.cancelBooking(3);
		return "index";
	}
	
}
