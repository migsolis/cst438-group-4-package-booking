package com.cst438.package_booking.domain;

import java.time.LocalDate;

public class Hotel {
	private int hotelId;
	private String hotelName;
	private Room roomInfo;
	private LocalDate checkinDate;
	private LocalDate checkoutDate;
	
	public Hotel(int hotelId, String hotelName) {
		super();
		this.hotelId = hotelId;
		this.hotelName = hotelName;
	}

	public Hotel(int hotelId, String hotelName, LocalDate checkinDate, LocalDate checkoutDate) {
		super();
		this.hotelId = hotelId;
		this.hotelName = hotelName;
		this.checkinDate = checkinDate;
		this.checkoutDate = checkoutDate;
	}

	public Hotel(int hotelId, String hotelName, Room roomInfo) {
		super();
		this.hotelId = hotelId;
		this.hotelName = hotelName;
		this.roomInfo = roomInfo;
	}

	public int getHotelId() {
		return hotelId;
	}

	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public LocalDate getCheckinDate() {
		return checkinDate;
	}

	public void setCheckinDate(LocalDate checkinDate) {
		this.checkinDate = checkinDate;
	}

	public LocalDate getCheckoutDate() {
		return checkoutDate;
	}

	public void setCheckoutDate(LocalDate checkoutDate) {
		this.checkoutDate = checkoutDate;
	}

	public Room getRoomInfo() {
		return roomInfo;
	}

	public void setRoomInfo(Room roomInfo) {
		this.roomInfo = roomInfo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + hotelId;
		result = prime * result + ((hotelName == null) ? 0 : hotelName.hashCode());
		result = prime * result + ((roomInfo == null) ? 0 : roomInfo.hashCode());
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
		if (hotelId != other.hotelId)
			return false;
		if (hotelName == null) {
			if (other.hotelName != null)
				return false;
		} else if (!hotelName.equals(other.hotelName))
			return false;
		if (roomInfo == null) {
			if (other.roomInfo != null)
				return false;
		} else if (!roomInfo.equals(other.roomInfo))
			return false;
		return true;
	}
	
}
