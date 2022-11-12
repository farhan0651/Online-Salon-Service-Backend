package com.capg.dto;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import com.capg.entity.SalonService;


public class SalonServicedto {

	//@NotNull(message = "Please provide valid serviceId")
	private long serviceId;
	private String serviceName;
	private String servicePrice;
	private String serviceDuration;
	private int discount;
	private String url;
	public static SalonServicedto entityToDTO(SalonService salon1) {
		SalonServicedto salon2 = new SalonServicedto();
		salon2.setServiceId(salon1.getServiceId());
		salon2.setServiceName(salon1.getServiceName());
		salon2.setServicePrice(salon1.getServicePrice());
		salon2.setServiceDuration(salon1.getServiceDuration());
		salon2.setDiscount(salon1.getDiscount());
		return salon2;
		
	}		
	public SalonServicedto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SalonServicedto(long serviceId, String serviceName, String servicePrice, String serviceDuration,
			int discount,String url) {
		super();
		this.serviceId = serviceId;
		this.serviceName = serviceName;
		this.servicePrice = servicePrice;
		this.serviceDuration = serviceDuration;
		this.discount = discount;
		this.url=url;
	}
	public long getServiceId() {
		return serviceId;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setUrl(String url) {
		this.url=url;
	}
	public String getUrl() {
		return this.url;
	}
	public String getServicePrice() {
		return servicePrice;
	}
	public String getServiceDuration() {
		return serviceDuration;
	}
	public int getDiscount() {
		return discount;
	}
	public void setServiceId(long serviceId) {
		this.serviceId = serviceId;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public void setServicePrice(String servicePrice) {
		this.servicePrice = servicePrice;
	}
	public void setServiceDuration(String serviceDuration) {
		this.serviceDuration = serviceDuration;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	@Override
	public String toString() {
		return "SalonServicedto [serviceId=" + serviceId + ", serviceName=" + serviceName + ", servicePrice="
				+ servicePrice + ", serviceDuration=" + serviceDuration + ", discount=" + discount + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(discount, serviceDuration, serviceId, serviceName, servicePrice);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SalonServicedto other = (SalonServicedto) obj;
		return discount == other.discount && Objects.equals(serviceDuration, other.serviceDuration)
				&& serviceId == other.serviceId && Objects.equals(serviceName, other.serviceName)
				&& Objects.equals(servicePrice, other.servicePrice);
	}
	
	
	
}