package com.capg.exception;

public class AppointmentAlreadyExistsException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public AppointmentAlreadyExistsException(String message){
		super(message);
		
	}
}
