package com.capg.dto;

import java.time.LocalDate;
import java.util.Objects;

import javax.validation.constraints.NotNull;

import com.capg.entity.Card;


public class Carddto {

	//@NotNull(message = "Please provide valid id")
	private long id;
	private String cardName;
	private String cardNumber;
	private String bankName ;
	private LocalDate expiryDate;
	
	public static Carddto entityToDTO(Card card2) {
		Carddto card3 = new Carddto();
		card3.setId(card2.getId());
		card3.setCardName(card2.getCardName());
		card3.setCardNumber(card2.getCardNumber());
		card3.setBankName(card2.getBankName());
		card3.setExpiryDate(card2.getExpiryDate());
		return card3;
		
	}
	public long getId() {
		return id;
	}
	public String getCardName() {
		return cardName;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public String getBankName() {
		return bankName;
	}
	public LocalDate getExpiryDate() {
		return expiryDate;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}
	@Override
	public String toString() {
		return "Carddto [id=" + id + ", cardName=" + cardName + ", cardNumber=" + cardNumber + ", bankName=" + bankName
				+ ", expiryDate=" + expiryDate + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(bankName, cardName, cardNumber, expiryDate, id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Carddto other = (Carddto) obj;
		return Objects.equals(bankName, other.bankName) && Objects.equals(cardName, other.cardName)
				&& Objects.equals(cardNumber, other.cardNumber) && Objects.equals(expiryDate, other.expiryDate)
				&& id == other.id;
	}
	
	
	
}