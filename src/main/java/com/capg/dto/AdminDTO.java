package com.capg.dto;


import com.capg.entity.Admin;

public class AdminDTO {
	private long userId;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	public Admin toAdmin() {
		Admin admin = new Admin();
		admin.setUserId(this.getUserId());
		return admin;
	}

}
