package com.capg.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.dto.Carddto;
import com.capg.dto.Paymentdto;
import com.capg.entity.Card;
import com.capg.entity.Payment;
import com.capg.exception.CardAlreadyExistsException;
import com.capg.exception.CardNotFoundException;
import com.capg.exception.PaymentServiceNotFoundException;
import com.capg.repository.ICardRepository;

@Service(value = "cardService")
@Transactional

public class ICardServiceImp implements ICardService{
	
	@Autowired
	private ICardRepository iCardRepository;
	
	//Get Card Info
		@Override
	   public Carddto getCard(long cardId) throws CardNotFoundException{ 
			Optional<Card> optional = iCardRepository.findById(cardId);
			Card card  = optional.orElseThrow(() -> new CardNotFoundException("Service.Card_NOT_FOUND"));
			Carddto carddto = Carddto.entityToDTO(card);
			return carddto;
		}

		//Add Card 
		@Override
		public Carddto addCard(Carddto card) throws CardAlreadyExistsException{
			Optional<Card> cards = iCardRepository.findById(card.getCardId());
			if(cards.isPresent())
			{
				throw new CardAlreadyExistsException("Service.Card_ALREADY_EXISTS");
			}
			Card card1 = Card.DTOToentity(card);
			iCardRepository.save(card1);
			return card;
			
		}

		//GetAll Cards
		public List<Carddto> getAllCards() throws CardNotFoundException{
			
			List<Card> card = iCardRepository.findAll();
			if(card.isEmpty())
				throw new CardNotFoundException("Service.Card_NOT_FOUND");
			List<Carddto> cards = new ArrayList<>();
			card.forEach(c -> {
				cards.add(Carddto.entityToDTO(c));
			});
			return cards;
		}
		//Delete Card Information
				@Override
				public void deleteCard(long cardId) throws CardNotFoundException{
					Optional<Card> card1 = iCardRepository.findById(cardId);
					Card c = card1.orElseThrow(() -> new CardNotFoundException("Service.Card_NOT_FOUND"));
					iCardRepository.deleteById(cardId);
				}

}