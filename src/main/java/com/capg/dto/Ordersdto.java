package com.capg.dto;

import java.time.LocalDate;
import java.util.Objects;

import javax.validation.constraints.NotNull;

import com.capg.entity.Appointment;
import com.capg.entity.Customer;
import com.capg.entity.Orders;
import com.capg.entity.Payment;
import com.capg.entity.SalonService;

public class Ordersdto {

	//@NotNull(message = "Please provide valid orderId")
	private long orderId;
	private double amount;
	private LocalDate billingDate;
	
	private String paymentMethod;
	
	private Appointment appointment;
	
	public static Ordersdto entityToDTO(Orders order1) {
		Ordersdto order2 = new Ordersdto();
		order2.setOrderId(order1.getOrderId());
		order2.setAmount(order1.getAmount());
		order2.setBillingDate(order1.getBillingDate());
		order2.setPaymentMethod(order1.getPaymentMethod());
		
		order2.setAppointment(order1.getAppointment());
		
		return order2;
		
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
		return "Ordersdto [orderId=" + orderId + ", amount=" + amount + ", billingDate=" + billingDate
				+ ", paymentMethod=" + paymentMethod + ", appointment=" + appointment + "]";
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
		Ordersdto other = (Ordersdto) obj;
		return Double.doubleToLongBits(amount) == Double.doubleToLongBits(other.amount)
				&& Objects.equals(appointment, other.appointment) && Objects.equals(billingDate, other.billingDate)
				&& orderId == other.orderId && Objects.equals(paymentMethod, other.paymentMethod);
	}

	
	
}