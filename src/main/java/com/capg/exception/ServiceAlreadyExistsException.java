package com.capg.exception;

public class ServiceAlreadyExistsException extends Exception{
private static final long serialVersionUID = 1L;
	
	public ServiceAlreadyExistsException(String message){
		super(message);
		
	}
}
