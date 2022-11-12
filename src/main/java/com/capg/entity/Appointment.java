package com.capg.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.capg.dto.Appointmentdto;


@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private long appointmentId;
	private String location;
	private String visitType;
	private LocalDate preferredDate;
	private LocalTime preferredTime;
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "serviceId")
	private SalonService salonService;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name= "customerId")
	private Customer customer;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="paymentId")
	private Payment payment;
	public Appointment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Appointment(long appointmentId, String location, String visitType, LocalDate preferredDate,
			LocalTime preferredTime, SalonService salonService, Customer customer, Payment payment) {
		super();
		this.appointmentId = appointmentId;
		this.location = location;
		this.visitType = visitType;
		this.preferredDate = preferredDate;
		this.preferredTime = preferredTime;
		this.salonService = salonService;
		this.customer = customer;
		this.payment = payment;
	}
	public long getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(long appointmentId) {
		this.appointmentId = appointmentId;
	}
	public String getLocation() {
		return location;
	}
	public int getCustomerId() {
		return customer.getCustomerId();
	}
	public void setCustomerId(int customerId) {
		this.customer.setCustomerId(customerId);
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getVisitType() {
		return visitType;
	}
	public void setVisitType(String visitType) {
		this.visitType = visitType;
	}
	public LocalDate getPreferredDate() {
		return preferredDate;
	}
	public void setPreferredDate(LocalDate preferredDate) {
		this.preferredDate = preferredDate;
	}
	public LocalTime getPreferredTime() {
		return preferredTime;
	}
	public void setPreferredTime(LocalTime preferredTime) {
		this.preferredTime = preferredTime;
	}
	public SalonService getSalonService() {
		return salonService;
	}
	public void setSalonService(SalonService salonService) {
		this.salonService = salonService;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Payment getPayment() {
		return payment;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	@Override
	public String toString() {
		return "Appointment [appointmentId=" + appointmentId + ", location=" + location + ", visitType=" + visitType
				+ ", preferredDate=" + preferredDate + ", preferredTime=" + preferredTime + ", salonService="
				+ salonService + ", customer=" + customer + ", payment=" + payment + "]";
	}
	
	public static Appointment DTOtoentity(Appointmentdto appointment2) {
		Appointment appointment3 = new Appointment();
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
		Appointment other = (Appointment) obj;
		return appointmentId == other.appointmentId && Objects.equals(customer, other.customer)
				&& Objects.equals(location, other.location) && Objects.equals(payment, other.payment)
				&& Objects.equals(preferredDate, other.preferredDate)
				&& Objects.equals(preferredTime, other.preferredTime)
				&& Objects.equals(salonService, other.salonService) && Objects.equals(visitType, other.visitType);
	}
	
}
