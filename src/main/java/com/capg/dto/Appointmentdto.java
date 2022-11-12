package com.capg.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

import javax.validation.constraints.NotNull;

import com.capg.entity.Appointment;
import com.capg.entity.Customer;
import com.capg.entity.Payment;
import com.capg.entity.SalonService;

public class Appointmentdto {

	//@NotNull(message = "Please provide valid a")
	private long appointmentId;
	private String location;
	private String visitType;
	private LocalDate preferredDate;
	private LocalTime preferredTime;
	private SalonService salonService;
	private Customer customer;
	private Payment payment;
	public long getAppointmentId() {
		return appointmentId;
	}
//	public int getCustomerId() {
//		return this.customer.getCustomerId();
//	}
//	public void setCustomerId(int customerId) {
//		this.customer.setCustomerId(customerId);
//	}
	public String getLocation() {
		return location;
	}
	public String getVisitType() {
		return visitType;
	}
	public LocalDate getPreferredDate() {
		return preferredDate;
	}
	public LocalTime getPreferredTime() {
		return preferredTime;
	}
	public SalonService getSalonService() {
		return salonService;
	}
	public Customer getCustomer() {
		return customer;
	}
	public Payment getPayment() {
		return payment;
	}
	public void setAppointmentId(long appointmentId) {
		this.appointmentId = appointmentId;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public void setVisitType(String visitType) {
		this.visitType = visitType;
	}
	public void setPreferredDate(LocalDate preferredDate) {
		this.preferredDate = preferredDate;
	}
	public void setPreferredTime(LocalTime preferredTime) {
		this.preferredTime = preferredTime;
	}
	public void setSalonService(SalonService salonService) {
		this.salonService = salonService;
	}
	public void setCustomer(Customer customer) {
		this.customer =customer;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	@Override
	public String toString() {
		return "Appointmentdto [appointmentId=" + appointmentId + ", location=" + location + ", visitType=" + visitType
				+ ", preferredDate=" + preferredDate + ", preferredTime=" + preferredTime + ", salonService="
				+ salonService + ", customer=" + customer + ", payment=" + payment + "]";
	}
	
	public static Appointmentdto entityToDTO(Appointment appointment2) {
		Appointmentdto appointment3 = new Appointmentdto();
		appointment3.setAppointmentId(appointment2.getAppointmentId());
		appointment3.setLocation(appointment2.getLocation());
		appointment3.setVisitType(appointment2.getVisitType());
		appointment3.setPreferredDate(appointment2.getPreferredDate());
		appointment3.setPreferredTime(appointment2.getPreferredTime());
		return appointment3;
		
	}
	
	
	
	@Override
	public int hashCode() {
		return Objects.hash(appointmentId, customer, location, payment, preferredDate, preferredTime, salonService,
				visitType);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Appointmentdto other = (Appointmentdto) obj;
		return appointmentId == other.appointmentId && Objects.equals(customer, other.customer)
				&& Objects.equals(location, other.location) && Objects.equals(payment, other.payment)
				&& Objects.equals(preferredDate, other.preferredDate)
				&& Objects.equals(preferredTime, other.preferredTime)
				&& Objects.equals(salonService, other.salonService) && Objects.equals(visitType, other.visitType);
	}
	
	
}
