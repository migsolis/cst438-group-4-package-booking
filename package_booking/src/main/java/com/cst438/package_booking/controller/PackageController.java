package com.cst438.package_booking.controller;

import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cst438.package_booking.domain.SearchDetails;

@Controller
public class PackageController {

	// Returns the form for a package search
	@GetMapping("/search/new")
	public String getPackages(Model model) {
		SearchDetails details = new SearchDetails();

		model.addAttribute("searchDetails", details);
		model.addAttribute("now", LocalDate.now());
		
		return "search_form";
	}
	
	@PostMapping("/search/new")
	public String processSearchForm(@Valid SearchDetails searchDetails, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			model.addAttribute("now", LocalDate.now());
			
			return "search_form";
		}
		
		return "search_results";
	}
}
