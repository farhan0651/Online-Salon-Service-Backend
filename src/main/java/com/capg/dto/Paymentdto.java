package com.capg.dto;


import java.util.Objects;

import javax.validation.constraints.NotNull;

import com.capg.entity.Card;
import com.capg.entity.Payment;

public class Paymentdto {
	//@NotNull(message = "Please provide valid paymentId")
	private long paymentId;
	private String type;
	private String status;
	private Card card;
	public static Paymentdto entityToDTO(Payment payment2) {
		Paymentdto pay3 = new Paymentdto();
		pay3.setPaymentId(payment2.getPaymentId());
		pay3.setType(payment2.getType());
		pay3.setStatus(payment2.getStatus());
		pay3.setCard(payment2.getCard());
		return pay3;
		
	}
	@Override
	public int hashCode() {
		return Objects.hash(card, paymentId, status, type);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Paymentdto other = (Paymentdto) obj;
		return Objects.equals(card, other.card) && paymentId == other.paymentId
				&& Objects.equals(status, other.status) && Objects.equals(type, other.type);
	}
	public long getPaymentId() {
		return paymentId;
	}
	public String getType() {
		return type;
	}
	public String getStatus() {
		return status;
	}
	public Card getCard() {
		return card;
	}
	public void setPaymentId(long paymentId) {
		this.paymentId = paymentId;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setCard(Card card) {
		this.card = card;
	}
	@Override
	public String toString() {
		return "Paymentdto [paymentId=" + paymentId + ", type=" + type + ", status=" + status + ", card=" + card
				+ "]";
	}
	
	
	
}