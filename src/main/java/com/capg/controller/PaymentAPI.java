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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.capg.dto.Paymentdto;

import com.capg.exception.PaymentAlreadyExistsException;
import com.capg.exception.PaymentServiceNotFoundException;
import com.capg.service.IPaymentService;


@RestController
@RequestMapping(value= "/payment")
@Validated
public class PaymentAPI {
	@Autowired
	private IPaymentService  iPaymentService;
	
	@Autowired
	private Environment environment;
	
	public static final Log LOGGER=LogFactory.getLog(PaymentAPI.class);
	
	@CrossOrigin(origins="http://localhost:3000")
	@GetMapping(value = "/{paymentId}")
	public ResponseEntity<Paymentdto> getPaymentDetails(@PathVariable @Min(value=1,message ="Please give paymentId >=1") Long paymentId) throws PaymentServiceNotFoundException{
		Paymentdto payment = iPaymentService.getPaymentDetails(paymentId); 
		LOGGER.info(environment.getProperty("getPaymentbyId"));
		return new ResponseEntity<>(payment, HttpStatus.OK);
	}
	
	@CrossOrigin(origins="http://localhost:3000")
	@GetMapping(value = "/getAllPayment")
	public ResponseEntity<List<Paymentdto>> getAllPaymentDetails() throws PaymentServiceNotFoundException {
		List<Paymentdto> paymentDetailsList = iPaymentService.getAllPaymentDetails();
		LOGGER.info(environment.getProperty("getAllPayment"));
		return new ResponseEntity<>(paymentDetailsList, HttpStatus.OK);
	}
	
	@CrossOrigin(origins="http://localhost:3000")
	@PutMapping(value = "/updatePayment/{paymentId}")
	public ResponseEntity<String> updatePayment(@PathVariable @Min(value=1,message ="Please give paymentId >=1") Long paymentId, @RequestBody Paymentdto payment)
			throws PaymentServiceNotFoundException {
		iPaymentService.updatePayment(paymentId, payment);
		String successMessage = environment.getProperty("update payment");
		LOGGER.info(successMessage);
		return new ResponseEntity<>(successMessage,HttpStatus.OK);
		
	}
	
	@CrossOrigin(origins="http://localhost:3000")
	@DeleteMapping(value = "/deletePayment/{paymentId}")
	public ResponseEntity<String> deletePayment(@PathVariable @Min(value=1,message ="Please give paymentId >=1") Long paymentId)
			throws PaymentServiceNotFoundException {
		iPaymentService.deletePayment(paymentId);
		String successMessage = environment.getProperty("PaymentDeletedSuccessfully");
		LOGGER.info(successMessage);
		return new ResponseEntity<>(successMessage ,HttpStatus.OK);
	}
	
	@CrossOrigin(origins="http://localhost:3000")
	@PostMapping(value = "/addPayment")
	public ResponseEntity<String> addPayment(@Valid @RequestBody Paymentdto payment) throws PaymentAlreadyExistsException {
		Paymentdto paymentId = iPaymentService.addPayment(payment);
		String successMessage = environment.getProperty("PaymentAddedSuccessfully") + paymentId;
		LOGGER.info(successMessage);
		return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
	}
	
	

}