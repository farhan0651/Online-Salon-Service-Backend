package com.capg.exception;

public class CardAlreadyExistsException extends Exception{
private static final long serialVersionUID = 1L;
	
	public CardAlreadyExistsException(String message){
		super(message);
		
	}
}