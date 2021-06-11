package com.cst438.package_booking.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
public class Booking {
	@Id
	@GeneratedValue
	private int bookingId;
	
	private int userId;
	
	private int adults;
	
	private int children;
	
	private int status;
	
	private String confirmation;
	
	@DateTimeFormat(iso=ISO.DATE_TIME)
	private LocalDateTime departureDate;
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private LocalDateTime returnDate;
	
	private LocalDateTime transactionDate;
	
	public Booking() {
		
	}

	public Booking(int bookingId, int userId, int adults, int children, int status, String confirmation,
			LocalDateTime departureDate, LocalDateTime returnDate, LocalDateTime transactionDate) {
		super();
		this.bookingId = bookingId;
		this.userId = userId;
		this.adults = adults;
		this.children = children;
		this.status = status;
		this.confirmation = confirmation;
		this.departureDate = departureDate;
		this.returnDate = returnDate;
		this.transactionDate = transactionDate;
	}
	
	public Booking(PackageInfo pk, int userId, int adults, int children) {
		FlightInfo flight = pk.getFlightInfo();
		Hotel hotel = pk.getHotel();
		this.userId = userId;
		this.adults = adults;
		this.children = children;
		this.departureDate = flight.getDepartureDate();
		this.returnDate = pk.getReturnDate().atTime(11, 0);
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getConfirmation() {
		return confirmation;
	}

	public void setConfirmation(String confirmation) {
		this.confirmation = confirmation;
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
		result = prime * result + bookingId;
		result = prime * result + children;
		result = prime * result + ((confirmation == null) ? 0 : confirmation.hashCode());
		result = prime * result + ((departureDate == null) ? 0 : departureDate.hashCode());
		result = prime * result + ((returnDate == null) ? 0 : returnDate.hashCode());
		result = prime * result + status;
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
		if (bookingId != other.bookingId)
			return false;
		if (children != other.children)
			return false;
		if (confirmation == null) {
			if (other.confirmation != null)
				return false;
		} else if (!confirmation.equals(other.confirmation))
			return false;
		if (departureDate == null) {
			if (other.departureDate != null)
				return false;
		} else if (!departureDate.equals(other.departureDate))
			return false;
		if (returnDate == null) {
			if (other.returnDate != null)
				return false;
		} else if (!returnDate.equals(other.returnDate))
			return false;
		if (status != other.status)
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

	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", userId=" + userId + ", adults=" + adults + ", children="
				+ children + ", status=" + status + ", confirmation=" + confirmation + ", departureDate="
				+ departureDate + ", returnDate=" + returnDate + ", transactionDate=" + transactionDate + "]";
	}
	
	
}
