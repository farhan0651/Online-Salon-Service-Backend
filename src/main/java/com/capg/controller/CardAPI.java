package com.capg.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.dto.Carddto;
import com.capg.exception.CardAlreadyExistsException;
import com.capg.exception.CardNotFoundException;
import com.capg.service.ICardService;

@RestController
@RequestMapping(value= "/card")
@Validated
public class CardAPI {
	
	@Autowired
	private ICardService iCardService;
	
	@Autowired
	private Environment environment;
	
	public static final Log LOGGER=LogFactory.getLog(PaymentAPI.class);
	
	@CrossOrigin(origins="http://localhost:3000")
	@PostMapping(value = "addCard")
	public ResponseEntity<String> addCard(@Valid @RequestBody Carddto card) throws CardAlreadyExistsException {
		Carddto cardId = iCardService.addCard(card);
		String successMessage = environment.getProperty("CardAddedSuccessfully") + cardId;
		LOGGER.info(successMessage);
		return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
	}
	
	@CrossOrigin(origins="http://localhost:3000")
	@GetMapping(value = "{id}")
	public ResponseEntity<Carddto> getCard(@PathVariable @Min(value=1,message ="Please give id >=1") Long id) throws CardNotFoundException{
		Carddto card = iCardService.getCard(id); 
		LOGGER.info(environment.getProperty("getCardbyId"));
		return new ResponseEntity<>(card, HttpStatus.OK);
	}
	@CrossOrigin(origins="http://localhost:3000")
	@GetMapping(value = "/getAllCards")
	public ResponseEntity<List<Carddto>> getAllCards() throws CardNotFoundException {
		List<Carddto> cardsList = iCardService.getAllCards();
		LOGGER.info(environment.getProperty("getAllCards"));
		return new ResponseEntity<>(cardsList, HttpStatus.OK);
	}
	
	@CrossOrigin(origins="http://localhost:3000")
	@DeleteMapping(value = "/deleteCard/{id}")
	public ResponseEntity<String> deleteCard(@PathVariable @Min(value=1,message ="Please give id >=1") Long id)
			throws CardNotFoundException {
		iCardService.deleteCard(id);
		String successMessage = environment.getProperty("CardDeletedSuccessfully");
		LOGGER.info(successMessage);
		return new ResponseEntity<>(successMessage ,HttpStatus.OK);
	}
}