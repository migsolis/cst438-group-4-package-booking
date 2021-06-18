package com.cst438.package_booking.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Car {
	@Id
	private int id;
	private String rentalCompany;
	private String carClass;
	private String description;
	private double pricePerDay;
	private double totalPrice;
	private int bookingId;
	
	public Car() {
		
	}

	public Car(String rentalCompany, String carClass, double pricePerDay) {
		super();
		this.rentalCompany = rentalCompany;
		this.carClass = carClass;
		this.pricePerDay = pricePerDay;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRentalCompany() {
		return rentalCompany;
	}

	public void setRentalCompany(String rentalCompany) {
		this.rentalCompany = rentalCompany;
	}

	public String getCarClass() {
		return carClass;
	}

	public void setCarClass(String carClass) {
		this.carClass = carClass;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPricePerDay() {
		return pricePerDay;
	}

	public void setPricePerDay(double pricePerDay) {
		this.pricePerDay = pricePerDay;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((carClass == null) ? 0 : carClass.hashCode());
		long temp;
		temp = Double.doubleToLongBits(pricePerDay);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((rentalCompany == null) ? 0 : rentalCompany.hashCode());
		result = prime * result + id;
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
		Car other = (Car) obj;
		if (carClass == null) {
			if (other.carClass != null)
				return false;
		} else if (!carClass.equals(other.carClass))
			return false;
		if (Double.doubleToLongBits(pricePerDay) != Double.doubleToLongBits(other.pricePerDay))
			return false;
		if (rentalCompany == null) {
			if (other.rentalCompany != null)
				return false;
		} else if (!rentalCompany.equals(other.rentalCompany))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

}
