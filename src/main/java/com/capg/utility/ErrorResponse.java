package com.capg.utility;

public class ErrorResponse {
	// Data fields
	private String message;
	private int errorCode;
	
	// Constructors
	public ErrorResponse() {
		super();
	}	
	public ErrorResponse(String message, int errorCode) {
		super();
		this.message = message;
		this.errorCode = errorCode;
	}

	// Getters and Setters
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}	
}
