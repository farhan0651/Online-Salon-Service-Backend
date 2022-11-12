package com.capg.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.capg.dto.AdminDTO;

@Entity
public class Admin {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long userId;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	public AdminDTO toAdminDTO() {
		AdminDTO adminDTO = new AdminDTO();
		adminDTO.setUserId(this.getUserId());
		return adminDTO;
	}

}
