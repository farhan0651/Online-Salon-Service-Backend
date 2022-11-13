package com.capg.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int door_no;
	private String street;
	private String area;
	private String city;
	private String state;
	private int pincode;
	public Address(int door_no, String street, String area, String city, String state, int pincode) {
		super();
		this.door_no = door_no;
		this.street = street;
		this.area = area;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
	}
	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getDoor_no() {
		return door_no;
	}
	public void setDoor_no(int door_no) {
		this.door_no = door_no;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	@Override
	public String toString() {
		return "Address [door_no=" + door_no + ", street=" + street + ", area=" + area + ", city=" + city + ", state="
				+ state + ", pincode=" + pincode + "]";
	}
	
	
}
