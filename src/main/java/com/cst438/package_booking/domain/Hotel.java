package com.cst438.package_booking.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;

@JsonIgnoreProperties("image")
public class Hotel {
	private int id;
	private String name;
	private String address;
	private String city;
	private String state;
	private String country;
	private String zipCode;
	private String phone;
	private String numberOfStars;
	private double avgRating;
	private String amenities;
	private String landmarks;
	private List<Room> rooms;
	
	public Hotel() {
		
	}
	
	public Hotel(int id, String hotelName) {
		super();
		this.id = id;
		this.name = hotelName;
	}

	public int getId() {
		return id;
	}
	
	@JsonSetter("id")
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	@JsonSetter
	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}
	
	@JsonSetter("street_address")
	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}
	@JsonSetter("city")
	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}
	
	@JsonSetter("state")
	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}
	
	@JsonSetter("country")
	public void setCountry(String country) {
		this.country = country;
	}

	public String getZipCode() {
		return zipCode;
	}
	
	@JsonSetter("zip_code")
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getPhone() {
		return phone;
	}

	@JsonSetter("phone")
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getNumberOfStars() {
		return numberOfStars;
	}

	@JsonSetter("number_of_stars")
	public void setNumberOfStars(String numberOfStars) {
		this.numberOfStars = numberOfStars;
	}

	public double getAvgRating() {
		return avgRating;
	}
	
	@JsonSetter("average_rating")
	public void setAvgRating(double avgRating) {
		this.avgRating = avgRating;
	}

	public String getAmenities() {
		return amenities;
	}
	
	@JsonSetter("amenities")
	public void setAmenities(String amenities) {
		this.amenities = amenities;
	}

	public String getLandmarks() {
		return landmarks;
	}

	@JsonSetter("landmarks")
	public void setLandmarks(String landmarks) {
		this.landmarks = landmarks;
	}

	public List<Room> getRooms() {
		return rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((amenities == null) ? 0 : amenities.hashCode());
		long temp;
		temp = Double.doubleToLongBits(avgRating);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + id;
		result = prime * result + ((landmarks == null) ? 0 : landmarks.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((numberOfStars == null) ? 0 : numberOfStars.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((rooms == null) ? 0 : rooms.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((zipCode == null) ? 0 : zipCode.hashCode());
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
		Hotel other = (Hotel) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (amenities == null) {
			if (other.amenities != null)
				return false;
		} else if (!amenities.equals(other.amenities))
			return false;
		if (Double.doubleToLongBits(avgRating) != Double.doubleToLongBits(other.avgRating))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (id != other.id)
			return false;
		if (landmarks == null) {
			if (other.landmarks != null)
				return false;
		} else if (!landmarks.equals(other.landmarks))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (numberOfStars == null) {
			if (other.numberOfStars != null)
				return false;
		} else if (!numberOfStars.equals(other.numberOfStars))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (rooms == null) {
			if (other.rooms != null)
				return false;
		} else if (!rooms.equals(other.rooms))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (zipCode == null) {
			if (other.zipCode != null)
				return false;
		} else if (!zipCode.equals(other.zipCode))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Hotel [id=" + id + ", name=" + name + ", address=" + address + ", city=" + city + ", state="
				+ state + ", country=" + country + ", zipCode=" + zipCode + ", phone=" + phone + ", numberOfStars="
				+ numberOfStars + ", avgRating=" + avgRating + ", amenities=" + amenities + ", landmarks=" + landmarks
				+ ", rooms=" + rooms + "]";
	}
	
}
