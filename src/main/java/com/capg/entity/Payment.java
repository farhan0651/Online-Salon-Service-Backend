package com.capg.entity;


import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.capg.dto.Paymentdto;
@Entity
public class Payment {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long paymentId;
	private String type;
	private String status;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="cardId")
	private Card card;
	public static Payment DTOToentity(Paymentdto paymentDTO) {
		Payment pay2 = new Payment();
		pay2.setPaymentId(paymentDTO.getPaymentId());
		pay2.setType(paymentDTO.getType());
		pay2.setStatus(paymentDTO.getStatus());
		pay2.setCard(paymentDTO.getCard());
		return pay2;
		
	}
	public Payment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Payment(long paymentId, String type, String status, Card card) {
		super();
		this.paymentId = paymentId;
		this.type = type;
		this.status = status;
		this.card = card;
	}
	public long getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(long paymentId) {
		this.paymentId = paymentId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Card getCard() {
		return card;
	}
	public void setCard(Card card) {
		this.card = card;
	}
	@Override
	public String toString() {
		return "Payment [paymentId=" + paymentId + ", type=" + type + ", status=" + status + ", card=" + card + "]";
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
		Payment other = (Payment) obj;
		return Objects.equals(card, other.card) && paymentId == other.paymentId
				&& Objects.equals(status, other.status) && Objects.equals(type, other.type);
	}
	
	

}