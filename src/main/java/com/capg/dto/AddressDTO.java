package com.capg.dto;

import javax.validation.constraints.NotNull;

public class AddressDTO {

	//@NotNull(message = "Please provide valid doorno")
	private int door_no;
	private String street;
	private String area;
	private String city; 
	private String state;
	private int pincode;
	public int getDoor_no() {
		return door_no;
	}
	public String getStreet() {
		return street;
	}
	public String getArea() {
		return area;
	}
	public String getCity() {
		return city;
	}
	public String getState() {
		return state;
	}
	public int getPincode() {
		return pincode;
	}
	public void setDoor_no(int door_no) {
		this.door_no = door_no;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	@Override
	public String toString() {
		return "Addressdto [door_no=" + door_no + ", street=" + street + ", area=" + area + ", city=" + city
				+ ", state=" + state + ", pincode=" + pincode + "]";
	}
	
	
	
}
