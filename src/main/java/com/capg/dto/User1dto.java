package com.capg.dto;

import javax.validation.constraints.NotNull;

import com.capg.entity.User1;

public class User1dto {

	//@NotNull(message = "Please provide valid user1Id")
	private long userId;
	@NotNull(message="password should not be empty")
	private String password;
	@NotNull(message="User Name should not be empty")
	private String userName;
	public long getUserId() {
		return userId;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	// conversion method
		public User1 toUser() {
			User1 user=new User1();
			user.setUserId(this.getUserId());
			user.setPassword(this.getPassword());
			user.setUserName(this.getUserName());
			return user;
		}
	
	@Override
	public String toString() {
		return "User1dto [user1Id=" + userId + ", password=" + password + "]";
	}
	
	
}
