package com.cst438.package_booking.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

//@Entity
//@Table(name="package_bookings")
public class PackageBooking {
	
//	@Id
	private int id;
	private int room_id;
	private int customer_id;
	private double total_price;
	private String check_in_date;
	private String check_out_date;
	private int number_occupants;
	
	public PackageBooking() { }

	public PackageBooking(int id, int room_id, int customer_id, double total_price, String check_in_date,
	      String check_out_date, int number_occupants)
	{
		super();
		this.id = id;
		this.room_id = room_id;
		this.customer_id = customer_id;
		this.total_price = total_price;
		this.check_in_date = check_in_date;
		this.check_out_date = check_out_date;
		this.number_occupants = number_occupants;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getRoom_id()
	{
		return room_id;
	}

	public void setRoom_id(int room_id)
	{
		this.room_id = room_id;
	}

	public int getCustomer_id()
	{
		return customer_id;
	}

	public void setCustomer_id(int customer_id)
	{
		this.customer_id = customer_id;
	}

	public double getTotal_price()
	{
		return total_price;
	}

	public void setTotal_price(double total_price)
	{
		this.total_price = total_price;
	}

	public String getCheck_in_date()
	{
		return check_in_date;
	}

	public void setCheck_in_date(String check_in_date)
	{
		this.check_in_date = check_in_date;
	}

	public String getCheck_out_date()
	{
		return check_out_date;
	}

	public void setCheck_out_date(String check_out_date)
	{
		this.check_out_date = check_out_date;
	}

	public int getNumber_occupants()
	{
		return number_occupants;
	}

	public void setNumber_occupants(int number_occupants)
	{
		this.number_occupants = number_occupants;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((check_in_date == null) ? 0 : check_in_date.hashCode());
		result = prime * result + ((check_out_date == null) ? 0 : check_out_date.hashCode());
		result = prime * result + customer_id;
		result = prime * result + id;
		result = prime * result + number_occupants;
		result = prime * result + room_id;
		long temp;
		temp = Double.doubleToLongBits(total_price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PackageBooking other = (PackageBooking) obj;
		if (check_in_date == null)
		{
			if (other.check_in_date != null)
				return false;
		} else if (!check_in_date.equals(other.check_in_date))
			return false;
		if (check_out_date == null)
		{
			if (other.check_out_date != null)
				return false;
		} else if (!check_out_date.equals(other.check_out_date))
			return false;
		if (customer_id != other.customer_id)
			return false;
		if (id != other.id)
			return false;
		if (number_occupants != other.number_occupants)
			return false;
		if (room_id != other.room_id)
			return false;
		if (Double.doubleToLongBits(total_price) != Double.doubleToLongBits(other.total_price))
			return false;
		return true;
	}

}