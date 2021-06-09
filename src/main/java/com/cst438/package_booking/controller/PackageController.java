package com.cst438.package_booking.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cst438.package_booking.domain.Flight;
import com.cst438.package_booking.domain.PackageInfo;
import com.cst438.package_booking.domain.SearchDetails;
import com.cst438.package_booking.service.FlightService;
import com.cst438.package_booking.service.PackageService;


@Controller
public class PackageController {
	
	@Autowired
	private FlightService flightService;
	
	@Autowired
	PackageService packageService;
	
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
		
		if(result.hasErrors()) {
			model.addAttribute("now", searchDetails.getDepartureDate());
			
			return "search_form";
		}
		
		List<PackageInfo> packages = packageService.getPackages(searchDetails);
		
		model.addAttribute("packages", packages);
		
		return "search_results";
	}
	
}
