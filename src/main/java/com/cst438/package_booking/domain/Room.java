package com.cst438.package_booking.domain;

public class Room {
	private int hotelId;
	private double pricePerNight;
	private int maxOccupants;
	private String bedType;
	private int numberOfBeds;
	
	public Room(double pricePerNight, int maxOccupants, String bedType) {
		super();
		this.pricePerNight = pricePerNight;
		this.maxOccupants = maxOccupants;
		this.bedType = bedType;
	}

	public int getHotelId() {
		return hotelId;
	}

	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}

	public double getPricePerNight() {
		return pricePerNight;
	}

	public void setPricePerNight(double pricePerNight) {
		this.pricePerNight = pricePerNight;
	}

	public int getMaxOccupants() {
		return maxOccupants;
	}

	public void setMaxOccupants(int maxOccupants) {
		this.maxOccupants = maxOccupants;
	}

	public String getBedType() {
		return bedType;
	}

	public void setBedType(String bedType) {
		this.bedType = bedType;
	}

	public int getNumberOfBeds() {
		return numberOfBeds;
	}

	public void setNumberOfBeds(int numberOfBeds) {
		this.numberOfBeds = numberOfBeds;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bedType == null) ? 0 : bedType.hashCode());
		result = prime * result + hotelId;
		result = prime * result + maxOccupants;
		result = prime * result + numberOfBeds;
		long temp;
		temp = Double.doubleToLongBits(pricePerNight);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Room other = (Room) obj;
		if (bedType == null) {
			if (other.bedType != null)
				return false;
		} else if (!bedType.equals(other.bedType))
			return false;
		if (hotelId != other.hotelId)
			return false;
		if (maxOccupants != other.maxOccupants)
			return false;
		if (numberOfBeds != other.numberOfBeds)
			return false;
		if (Double.doubleToLongBits(pricePerNight) != Double.doubleToLongBits(other.pricePerNight))
			return false;
		return true;
	}
	
}