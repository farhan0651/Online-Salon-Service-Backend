package com.capg.entity;

import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import com.capg.dto.Customerdto;
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer customerId;
	private String name;
	private String email;
	private String contactNo;
	private LocalDate dob;
	
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "userId", unique = true, nullable=false)
	private User1 user1;
	
	//Relationship with Address
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "door_No", nullable=false)
	private Address address;


	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", name=" + name + ", email=" + email + ", contactNo=" + contactNo
				+ ", dob=" + dob + ", user1=" + user1 + ", address=" + address + "]";
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
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
	
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(Integer customerId, String name, String email, String contactNo, LocalDate dob, User1 user1,
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
	
	public static Customer DTOToentity(Customerdto customer2) {
		Customer customer3 = new Customer();
		customer3.setContactNo(customer2.getContactNo());
		customer3.setDob(customer2.getDob());
		customer3.setEmail(customer2.getEmail());
		customer3.setName(customer2.getName());
		customer3.setUser1(customer2.getUser1());
		customer3.setCustomerId(customer2.getCustomerId());
		customer3.setAddress(customer2.getAddress());
		return customer3;
		
	}

	
	
		
}