package com.capg.controller;

import java.util.List;
import java.util.logging.Logger;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.ThrowsAdvice;
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

import com.capg.dto.Customerdto;
import com.capg.entity.Customer;
import com.capg.exception.CustomerAlreadyExistsException;
import com.capg.exception.CustomerServiceNotFoundException;
import com.capg.exception.SalonServiceNotFoundException;
import com.capg.service.ICustomerService;


@RestController
@RequestMapping(value = "/Customer")
@Validated
@CrossOrigin("*")
public class CustomerAPI {

	@Autowired
	private ICustomerService icustomerService;
	
	@Autowired
	private Environment environment;
	
	public static final Log LOGGER=LogFactory.getLog(CustomerAPI.class);

	@CrossOrigin(origins="http://localhost:3000")
	@GetMapping(value = "/{customerId}")
	public ResponseEntity<Customerdto> getCustomer(@PathVariable @Min(value=1,message ="Please give customerId >=1") Integer customerId) throws CustomerServiceNotFoundException {
		Customerdto customer = icustomerService.getCustomer(customerId);
		LOGGER.info(environment.getProperty("getCustomerbyId"));
		return new ResponseEntity<>(customer, HttpStatus.OK); 
	}
	
	@CrossOrigin(origins="http://localhost:3000")
	@GetMapping(value = "/getAllCustomer") 
	public ResponseEntity<List<Customerdto>> getAllCustomers() throws CustomerServiceNotFoundException {
		List<Customerdto> customerList = icustomerService.getAllCustomers();
		LOGGER.info(environment.getProperty("ListOfCustomer"));
       return new ResponseEntity<>(customerList, HttpStatus.OK);
	}
	
	@CrossOrigin(origins="http://localhost:3000")
	@PostMapping(value = "/addCustomer")
	public ResponseEntity<String> addCustomer(@Valid @RequestBody Customerdto customer) throws CustomerAlreadyExistsException {
		Customer cust = icustomerService.addCustomer(customer);
		String successMessage = environment.getProperty("Customer_added_successfully") ;
		LOGGER.info(successMessage);
		return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
	}
	
	@CrossOrigin(origins="http://localhost:3000")
	@PutMapping(value = "/updateCustomer/{customerId}")
	public ResponseEntity<String> updateCustomer(@PathVariable @Min(value=1,message ="Please give customerId >=1") Integer customerId, @RequestBody Customerdto customer)
			throws CustomerServiceNotFoundException {
		icustomerService.updateCustomer(customerId, customer);
		String successMessage = environment.getProperty("UpdatedSuccessfully");
		LOGGER.info(successMessage);
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
	}
	

	@CrossOrigin(origins="http://localhost:3000")
	@DeleteMapping(value = "/deleteCustomer/{customerId}")
	public ResponseEntity<String> deleteCustomer(@PathVariable @Min(value=1,message ="Please give userId >=1") Integer customerId) throws CustomerServiceNotFoundException {
		icustomerService.deleteCustomer(customerId);
		String successMessage = environment.getProperty("deletedSuccessfully");
		LOGGER.info(successMessage);
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
	}   
}  
