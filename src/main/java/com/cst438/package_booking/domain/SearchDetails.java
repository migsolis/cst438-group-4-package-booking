package com.cst438.package_booking.domain;

import java.time.LocalDate;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;


public class SearchDetails {
	
	@NotNull
	@Min(1)
	@Max(3)
	private int packageType;
	
	@NotNull
	@Size(min=3, max=20)
	private String departureLocation;
	
	@NotNull
	@Size(min=3, max=20)
	private String destinationLocation;
	
	@NotNull
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate departureDate;
	
	@NotNull
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate returnDate;
	
	@NotNull
	@Min(1)
	@Max(4)
	private int adults;
	
	@NotNull
	@Min(0)
	@Max(4)
	private int children;
	
	public SearchDetails() {
		
	}
	
	public SearchDetails(int packageType, String departureLocation, String destinationLocation, LocalDate departureDate,
			LocalDate returnDate, int adults, int children) {
		super();
		this.packageType = packageType;
		this.departureLocation = departureLocation;
		this.destinationLocation = destinationLocation;
		this.departureDate = departureDate;
		this.returnDate = returnDate;
		this.adults = adults;
		this.children = children;
	}
	
	public int getPackageType() {
		return packageType;
	}
	
	public void setPackageType(int packageType) {
		this.packageType = packageType;
	}

	public String getDepartureLocation() {
		return departureLocation;
	}

	public void setDepartureLocation(String departureLocation) {
		this.departureLocation = departureLocation;
	}

	public String getDestinationLocation() {
		return destinationLocation;
	}

	public void setDestinationLocation(String destinationLocation) {
		this.destinationLocation = destinationLocation;
	}

	public LocalDate getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(LocalDate departureDate) {
		this.departureDate = departureDate;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}

	public int getAdults() {
		return adults;
	}

	public void setAdults(int adults) {
		this.adults = adults;
	}

	public int getChildren() {
		return children;
	}

	public void setChildren(int children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "SearchDetails [packageType=" + packageType + ", departureLocation="
				+ departureLocation + ", destinationLocation=" + destinationLocation + ", departureDate="
				+ departureDate + ", returnDate=" + returnDate + ", adults=" + adults + ", children=" + children + "]";
	}
	
}
