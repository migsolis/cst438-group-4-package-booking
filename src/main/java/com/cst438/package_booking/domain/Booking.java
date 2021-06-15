package com.cst438.package_booking.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
public class Booking {
	@Id
	@GeneratedValue
	private int id;
	private int packageType;
	private String destination;
	private String carInfo;
	private String flightInfo;
	private String hotelInfo;
	private int userId;
	private int adults;
	private int children;
	private double totalPrice;
	private String status;
	@DateTimeFormat(iso=ISO.DATE_TIME)
	private LocalDateTime departureDate;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private LocalDateTime returnDate;
	private LocalDateTime transactionDate;
	
	public Booking() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPackageType() {
		return packageType;
	}

	public void setPackageType(int packageType) {
		this.packageType = packageType;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getCarInfo() {
		return carInfo;
	}

	public void setCarInfo(String carInfo) {
		this.carInfo = carInfo;
	}

	public String getFlightInfo() {
		return flightInfo;
	}

	public void setFlightInfo(String flightInfo) {
		this.flightInfo = flightInfo;
	}

	public String getHotelInfo() {
		return hotelInfo;
	}

	public void setHotelInfo(String hotelInfo) {
		this.hotelInfo = hotelInfo;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(LocalDateTime departureDate) {
		this.departureDate = departureDate;
	}

	public LocalDateTime getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDateTime returnDate) {
		this.returnDate = returnDate;
	}

	public LocalDateTime getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDateTime transactionDate) {
		this.transactionDate = transactionDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + adults;
		result = prime * result + ((carInfo == null) ? 0 : carInfo.hashCode());
		result = prime * result + children;
		result = prime * result + ((departureDate == null) ? 0 : departureDate.hashCode());
		result = prime * result + ((destination == null) ? 0 : destination.hashCode());
		result = prime * result + ((flightInfo == null) ? 0 : flightInfo.hashCode());
		result = prime * result + ((hotelInfo == null) ? 0 : hotelInfo.hashCode());
		result = prime * result + id;
		result = prime * result + packageType;
		result = prime * result + ((returnDate == null) ? 0 : returnDate.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		long temp;
		temp = Double.doubleToLongBits(totalPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((transactionDate == null) ? 0 : transactionDate.hashCode());
		result = prime * result + userId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Booking other = (Booking) obj;
		if (adults != other.adults)
			return false;
		if (carInfo == null) {
			if (other.carInfo != null)
				return false;
		} else if (!carInfo.equals(other.carInfo))
			return false;
		if (children != other.children)
			return false;
		if (departureDate == null) {
			if (other.departureDate != null)
				return false;
		} else if (!departureDate.equals(other.departureDate))
			return false;
		if (destination == null) {
			if (other.destination != null)
				return false;
		} else if (!destination.equals(other.destination))
			return false;
		if (flightInfo == null) {
			if (other.flightInfo != null)
				return false;
		} else if (!flightInfo.equals(other.flightInfo))
			return false;
		if (hotelInfo == null) {
			if (other.hotelInfo != null)
				return false;
		} else if (!hotelInfo.equals(other.hotelInfo))
			return false;
		if (id != other.id)
			return false;
		if (packageType != other.packageType)
			return false;
		if (returnDate == null) {
			if (other.returnDate != null)
				return false;
		} else if (!returnDate.equals(other.returnDate))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (Double.doubleToLongBits(totalPrice) != Double.doubleToLongBits(other.totalPrice))
			return false;
		if (transactionDate == null) {
			if (other.transactionDate != null)
				return false;
		} else if (!transactionDate.equals(other.transactionDate))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}
}
