package com.capg.dto;

import java.time.LocalDate;
import java.util.Objects;

import javax.validation.constraints.NotNull;

import com.capg.entity.Address;
import com.capg.entity.Customer;
import com.capg.entity.User1;

public class Customerdto {

	//@NotNull(message = "Please provide valid userId")
	private int customerId;
	public int getCustomerId() {
		return customerId;
	}





	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}


	private String name;
	private String email;
	private String contactNo;
	private LocalDate dob;
	private User1 user1;
	private Address address;

	
	public static Customerdto entityToDTO(Customer customer2) {
		Customerdto customer3 = new Customerdto();
		customer3.setContactNo(customer2.getContactNo());
		customer3.setDob(customer2.getDob());
		customer3.setEmail(customer2.getEmail());
		customer3.setName(customer2.getName());
		customer3.setUser1(customer2.getUser1());
		customer3.setCustomerId(customer2.getCustomerId());
		customer3.setAddress(customer2.getAddress());
		return customer3;
		
	}


	


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getContactNo() {
		return contactNo;
	}


	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}


	public LocalDate getDob() {
		return dob;
	}


	public void setDob(LocalDate dob) {
		this.dob = dob;
	}


	public User1 getUser1() {
		return user1;
	}


	public void setUser1(User1 user1) {
		this.user1 = user1;
	}


	public Address getAddress() {
		return address;
	}


	public void setAddress(Address address) {
		this.address = address;
	}


	@Override
	public int hashCode() {
		return Objects.hash(address, contactNo, customerId, dob, email, name, user1);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customerdto other = (Customerdto) obj;
		return Objects.equals(address, other.address) && Objects.equals(contactNo, other.contactNo)
				&& Objects.equals(customerId, other.customerId) && Objects.equals(dob, other.dob)
				&& Objects.equals(email, other.email) && Objects.equals(name, other.name)
				&& Objects.equals(user1, other.user1);
	}


	@Override
	public String toString() {
		return "Customerdto [customerId=" + customerId + ", name=" + name + ", email=" + email + ", contactNo="
				+ contactNo + ", dob=" + dob + ", user1=" + user1 + ", address=" + address + "]";
	}


	public Customerdto(Integer customerId, String name, String email, String contactNo, LocalDate dob, User1 user1,
			Address address) {
		super();
		this.customerId = customerId;
		this.name = name;
		this.email = email;
		this.contactNo = contactNo;
		this.dob = dob;
		this.user1 = user1;
		this.address = address;
	}


	public Customerdto() {
		super();
		// TODO Auto-generated constructor stub
	}
	


	
	
	
	
	
	
	
}
