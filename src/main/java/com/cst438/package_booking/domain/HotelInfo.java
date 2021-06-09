package com.cst438.package_booking.domain;

import java.util.List;

public class HotelInfo {
	private int hotelId;
	private String hotelName;
	private RoomInfo roomInfo;
	
	public HotelInfo(int hotelId, String hotelName) {
		super();
		this.hotelId = hotelId;
		this.hotelName = hotelName;
	}

	public HotelInfo(int hotelId, String hotelName, RoomInfo roomInfo) {
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

	public RoomInfo getRoomInfo() {
		return roomInfo;
	}

	public void setRoomInfo(RoomInfo roomInfo) {
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
		HotelInfo other = (HotelInfo) obj;
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
