package com.capg.exception;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.capg.utility.ErrorResponse;


@ControllerAdvice
public class GlobalException  {
	
	@Autowired
	private Environment environment;
	
	@ExceptionHandler(value= UserException.class)
	public ResponseEntity<ErrorResponse> userExceptionHandler(UserException exception){
		HttpStatus status = HttpStatus.NOT_FOUND;
		ErrorResponse error = new ErrorResponse(environment.getProperty(exception.getMessage()), status.value());
		return new ResponseEntity<ErrorResponse>(error, status);
	}
	
	@ExceptionHandler(value= AppointmentServiceNotFoundException.class)
	public ResponseEntity<String>AppointmentServiceNotFoundException(AppointmentServiceNotFoundException appointmentServiceNotFoundException){
		return new ResponseEntity<String>("Appointment not found, Try with another Appointment Id", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value=AppointmentAlreadyExistsException.class)
	public ResponseEntity<String> AppointmentAlreadyExistsException(AppointmentAlreadyExistsException appointmentAlreadyExistsException)
	{
		return new ResponseEntity<String>("Appointment Already Exists.",HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value=CustomerServiceNotFoundException.class)
	public ResponseEntity<String> CustomerServiceNotFoundException(CustomerServiceNotFoundException customerServiceNotFoundException)
	{
		return new ResponseEntity<String>("No service found.",HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value=CustomerAlreadyExistsException.class)
	public ResponseEntity<String> CustomerAlreadyExistsException(CustomerAlreadyExistsException customerAlreadyExistsException)
	{
		return new ResponseEntity<String>("Customer Already Exists.",HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = OrderServiceNotFoundException.class)
	public ResponseEntity<String> OrderServiceNotFoundException(OrderServiceNotFoundException orderServiceNotFoundException){
		return new ResponseEntity<String>("Order not found. Please try again",HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(value = OrderAlreadyExistsException.class)
	public ResponseEntity<String> OrderAlreadyExistsException(OrderAlreadyExistsException orderAlreadyExistsException){
		return new ResponseEntity<String>("Order already exists",HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(value= PaymentServiceNotFoundException.class)
	public ResponseEntity<String> PaymentServiceNotFoundException(PaymentServiceNotFoundException paymentServiceNotFoundException){
		return new ResponseEntity<String>("Payment Not found", HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(value= PaymentAlreadyExistsException.class)
	public ResponseEntity<String> PaymentAlreadyExistsException(PaymentAlreadyExistsException paymentAlreadyExistsException){
		return new ResponseEntity<String>("This payment has already been made, try with different payment id", HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(value=SalonServiceNotFoundException.class)
	public ResponseEntity<String> SalonServiceNotFoundException(SalonServiceNotFoundException salonServiceNotFoundException)
	{
		return new ResponseEntity<String>("Salon service not found.",HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value=ServiceAlreadyExistsException.class)
	public ResponseEntity<String> ServiceAlreadyExistsException(ServiceAlreadyExistsException serviceAlreadyExistsException)
	{
		return new ResponseEntity<String>("Salon service already exists.",HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value=UserServiceNotFoundException.class)
	public ResponseEntity<String> UserServiceNotFoundException(UserServiceNotFoundException userServiceNotFoundException)
	{
		return new ResponseEntity<String>("User service not found.",HttpStatus.NOT_FOUND);
	}
	


}