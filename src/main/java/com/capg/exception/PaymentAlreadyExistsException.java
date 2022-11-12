package com.capg.exception;

public class PaymentAlreadyExistsException extends Exception{
private static final long serialVersionUID = 1L;
	
	public PaymentAlreadyExistsException(String message){
		super(message);
		
	}
}
