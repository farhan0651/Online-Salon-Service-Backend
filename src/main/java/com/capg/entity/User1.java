package com.capg.entity;
import java.util.Objects;

import javax.persistence.*;

import com.capg.dto.User1dto;

@Entity
public class User1 {
	// Data Fields
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long userId; 
	@Column(unique=true)
	private String userName;
	private String password;
	
//	// Relationships
//	@OneToOne(cascade=CascadeType.ALL)
//	@JoinColumn(name="custId", unique=true)
//	private Customer customer;
	
	// Relationships
//	@OneToOne(cascade=CascadeType.ALL)
//	@JoinColumn(name="userId", unique=true)
//	private Admin admin;
	
	//getters and setters
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	
	public User1() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public User1(long userId, String userName, String password) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
	}
	@Override
	public int hashCode() {
		return Objects.hash(password, userId, userName);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User1 other = (User1) obj;
		return Objects.equals(password, other.password) && userId == other.userId
				&& Objects.equals(userName, other.userName);
	}
	@Override
	public String toString() {
		return "User1 [userId=" + userId + ", userName=" + userName + ", password=" + password + "]";
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	// conversion method
	public User1dto toUserDTO() {
		User1dto userDTO=new User1dto();
		userDTO.setUserId(this.getUserId());
		userDTO.setUserName(this.getUserName());
		userDTO.setPassword(this.getPassword());
		return userDTO;
	}
	
	// Overrides
	

}