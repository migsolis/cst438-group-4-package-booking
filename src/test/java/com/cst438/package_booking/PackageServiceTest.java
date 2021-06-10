package com.cst438.package_booking;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cst438.package_booking.domain.Car;
import com.cst438.package_booking.domain.FlightInfo;
import com.cst438.package_booking.domain.Hotel;
import com.cst438.package_booking.domain.PackageInfo;
import com.cst438.package_booking.domain.Room;
import com.cst438.package_booking.domain.SearchDetails;
import com.cst438.package_booking.service.CarService;
import com.cst438.package_booking.service.FlightService;
import com.cst438.package_booking.service.HotelService;
import com.cst438.package_booking.service.PackageService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@SpringBootTest
class PackageServiceTest {
	
	@MockBean
	private CarService mockCarService;
	
	@MockBean
	private FlightService mockFlightService;
	
	@MockBean
	private HotelService mockHotelService;
	
	private PackageService packageService;
	
	@BeforeEach
	public void setupEach() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void ValidSearchDetailsTest() {
		
		Car car = new Car("RentalCom1", "Luxury Sports Car", 1234.0);
		List<Car> cars = new ArrayList<Car>();
		cars.add(car);
		
////		FlightInfo flightInfo = new FlightInfo("City1", "City2", "First Class", LocalDateTime.of(2021, 6, 6, 5, 30), 2345.6);
//		List<FlightInfo> flights = new ArrayList<FlightInfo>();
//		flights.add(flightInfo);
		
		Room roomInfo = new Room(678.9, 4, "King");
		List<Room> rooms = new ArrayList<Room>();
		rooms.add(roomInfo);
		
		Hotel hotelInfo = new Hotel(123, "Hotel1");
		List<Hotel> hotels = new ArrayList<Hotel>();
		hotels.add(hotelInfo);
		
		SearchDetails testSearchDetails = new SearchDetails(3, "City1", "City2", 
				LocalDate.of(2021, 6, 6), LocalDate.of(2021, 6, 10), 2, 2);
		
		given(mockCarService.getAllCars("City2")).willReturn(cars);
//		given(mockFlightService.getFlights("City1", "City2", LocalDateTime.of(2021, 6, 6, 0, 0, 0))).willReturn(flights);
		given(mockHotelService.getHotels("City2", LocalDate.of(2021, 6, 6), 5)).willReturn(hotels);
		given(mockHotelService.getRooms(123, LocalDate.of(2021, 6, 6), 5)).willReturn(rooms);
		
		packageService = new PackageService(mockCarService, mockFlightService, mockHotelService);
		
		List<PackageInfo> packages = packageService.getPackages(testSearchDetails);
		
		assertEquals(cars, packages.get(0).getCarInfo());
//		assertEquals(flights, packages.get(0).getFlightInfo());
		assertEquals(hotels, packages.get(0).getHotelInfo());
		assertEquals(roomInfo, packages.get(0).getHotelInfo().getRoomInfo());
	}

}
