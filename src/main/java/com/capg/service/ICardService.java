package com.capg.service;

import java.util.List;

import com.capg.dto.Carddto;
import com.capg.exception.CardAlreadyExistsException;
import com.capg.exception.CardNotFoundException;

public interface ICardService {
	public Carddto getCard(long id) throws CardNotFoundException;
	public Carddto addCard(Carddto card) throws CardAlreadyExistsException;
	public void deleteCard(long id) throws CardNotFoundException;
	public List<Carddto> getAllCards() throws CardNotFoundException;
	
}