package com.cst438.package_booking.controller;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;


import com.cst438.package_booking.domain.Booking;
import com.cst438.package_booking.domain.Flight;
import com.cst438.package_booking.domain.PackageInfo;
import com.cst438.package_booking.domain.Room;
import com.cst438.package_booking.domain.SearchDetails;
import com.cst438.package_booking.domain.User;
import com.cst438.package_booking.service.BookingService;
import com.cst438.package_booking.service.FlightService;
import com.cst438.package_booking.service.PackageService;
import com.cst438.package_booking.service.UserService;


@Controller
@SessionAttributes({"searchDetails", "packages"})
public class PackageController {
	private static final Logger log = LoggerFactory.getLogger(PackageController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private FlightService flightService;
	
	@Autowired
	PackageService packageService;
	
	@GetMapping("/")
	public String packageHome(Model model) {
		
		return "index";
	}
	
	@GetMapping(value={"/login"})
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }
	
	@GetMapping(value="/registration")
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @PostMapping(value = "/registration")
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByUserName(user.getUsername());
        if (userExists != null) {
            bindingResult
                    .rejectValue("username", "error.user",
                            "There is already a user registered with the user name provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("registration");

        }
        return modelAndView;
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
		
//		SearchDetails savedSearch = searchRepository.save(searchDetails);
		
//		log.info("Searched saved: " + savedSearch.toString());
		
		List<PackageInfo> packages = packageService.getPackages(searchDetails);
		
		log.info("Packages returned...");
		int days = (int) ChronoUnit.DAYS.between(searchDetails.getDepartureDate(),
				searchDetails.getReturnDate());

		model.addAttribute("days", days);
		model.addAttribute("packages", packages);
//		model.addAttribute("searchDetails", savedSearch);
		
		return "search_results";
	}
	
	@GetMapping("/user/bookings")
	public String viewBookings(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByUserName(auth.getName());
		model.addAttribute("firstName", user.getFirstName());
		List<Booking> userBookings = bookingService.getBookingsForUser(user.getId());
		model.addAttribute("userBookings", userBookings);
		return "user_bookings";
	}
	
	@PostMapping("/booking/new")
	public String createNewBooking(
			@ModelAttribute("searchDetails") SearchDetails searchDetails,
			@ModelAttribute("packages") List<PackageInfo> packages,
			@RequestParam("index") int index,
			@RequestParam("roomIndex") int roomIndex,
			Model model) {
		
		log.info("Creating new booking for package: " + packages.get(index).toString());
		
		PackageInfo selectedPackage = packages.get(index);
		List<Room> selectedRoom = new ArrayList<Room>();
		selectedRoom.add(selectedPackage.getHotel().getRooms().get(roomIndex));
		selectedPackage.getHotel().setRooms(selectedRoom);
		
		Booking booking = bookingService.createBooking(1, searchDetails, selectedPackage);
		
		if(booking == null) {
			log.info("Booking failed");
		} else {
			log.info("Booking complete, booking id: " + String.valueOf(booking.getId()));
		}
		
		model.addAttribute("booking", booking);
		model.addAttribute("bookingId", (int) booking.getId());
		return "booking_status";
	}
	
	@PostMapping("/booking/cancel")
	public String cancelBooking(@ModelAttribute("bookingId") int bookingId, Model model) {
		log.info("Cancel booking :" + String.valueOf(bookingId));
		
		//TODO add user Id to cancelBooking call
		int userId = 1;
		boolean isCancelled = bookingService.cancelBooking(userId, bookingId);
		
		if(!isCancelled) return "search_form";
		
		return "index";
	}
	
}
