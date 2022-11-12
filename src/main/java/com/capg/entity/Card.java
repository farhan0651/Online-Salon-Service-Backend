package com.capg.entity;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.capg.dto.Carddto;

@Entity
public class Card {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String cardName;
	private String cardNumber;
	private String bankName ;
	private LocalDate expiryDate;
	
	public static Card DTOToentity(Carddto carddto) {
		Card card2 = new Card();
		card2.setId(carddto.getId());
		card2.setCardName(carddto.getCardName());
		card2.setCardNumber(carddto.getCardNumber());
		card2.setBankName(carddto.getBankName());
		card2.setExpiryDate(carddto.getExpiryDate());
		
		return card2;
		
	}
	
	public Card() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Card(long id, String cardName, String cardNumber, String bankName, LocalDate expiryDate) {
		super();
		this.id = id;
		this.cardName = cardName;
		this.cardNumber = cardNumber;
		this.bankName = bankName;
		this.expiryDate = expiryDate;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public LocalDate getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}
	@Override
	public String toString() {
		return "Card [id=" + id + ", cardName=" + cardName + ", cardNumber=" + cardNumber + ", bankName=" + bankName
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
		Card other = (Card) obj;
		return Objects.equals(bankName, other.bankName) && Objects.equals(cardName, other.cardName)
				&& Objects.equals(cardNumber, other.cardNumber) && Objects.equals(expiryDate, other.expiryDate)
				&& id == other.id;
	}
	
   
}