package com.capg.entity;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import com.capg.dto.Ordersdto;
@Entity
public class Orders {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long orderId;
	private double amount;
	private LocalDate billingDate;
	private String paymentMethod;
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "appointmentId")
	private Appointment appointment;
	
	
	public static Orders DTOToentity(Ordersdto order3) {
		
		Orders order4 = new Orders();
		order4.setOrderId(order3.getOrderId());
		order4.setAmount(order3.getAmount());
		order4.setBillingDate(order3.getBillingDate());
		order4.setPaymentMethod(order3.getPaymentMethod());
		order4.setAppointment(order3.getAppointment());
		
		return order4;
	}
	public Orders() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Orders(long orderId, double amount, LocalDate billingDate, String paymentMethod, Appointment appointment) {
		super();
		this.orderId = orderId;
		this.amount = amount;
		this.billingDate = billingDate;
		this.paymentMethod = paymentMethod;
		this.appointment = appointment;
	}
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public LocalDate getBillingDate() {
		return billingDate;
	}
	public void setBillingDate(LocalDate billingDate) {
		this.billingDate = billingDate;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public Appointment getAppointment() {
		return appointment;
	}
	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}
	@Override
	public String toString() {
		return "Orders [orderId=" + orderId + ", amount=" + amount + ", billingDate=" + billingDate + ", paymentMethod="
				+ paymentMethod + ", appointment=" + appointment + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(amount, appointment, billingDate, orderId, paymentMethod);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Orders other = (Orders) obj;
		return Double.doubleToLongBits(amount) == Double.doubleToLongBits(other.amount)
				&& Objects.equals(appointment, other.appointment) && Objects.equals(billingDate, other.billingDate)
				&& orderId == other.orderId && Objects.equals(paymentMethod, other.paymentMethod);
	}
	
	

}