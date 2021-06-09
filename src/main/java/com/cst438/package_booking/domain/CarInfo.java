package com.cst438.package_booking.domain;

public class CarInfo {
	private int rentalId;
	private String rentalCompany;
	private String carClass;
	private double pricePerDay;
	
	public CarInfo() {
		
	}

	public CarInfo(String rentalCompany, String carClass, double pricePerDay) {
		super();
		this.rentalCompany = rentalCompany;
		this.carClass = carClass;
		this.pricePerDay = pricePerDay;
	}

	public int getRentalId() {
		return rentalId;
	}

	public void setRentalId(int rentalId) {
		this.rentalId = rentalId;
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

	public double getPricePerDay() {
		return pricePerDay;
	}

	public void setPricePerDay(double pricePerDay) {
		this.pricePerDay = pricePerDay;
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
		result = prime * result + rentalId;
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
		CarInfo other = (CarInfo) obj;
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
		if (rentalId != other.rentalId)
			return false;
		return true;
	}

}
