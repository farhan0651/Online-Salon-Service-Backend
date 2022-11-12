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

import com.capg.dto.Ordersdto;
import com.capg.entity.Orders;
import com.capg.exception.OrderAlreadyExistsException;
import com.capg.exception.OrderServiceNotFoundException;
import com.capg.service.IOrderService;

@RestController
@RequestMapping(value = "/orders")
@Validated
public class OrdersAPI {

	
	@Autowired
	private IOrderService iOrderService;
	
	@Autowired
	private Environment environment;
	
	public static final Log LOGGER=LogFactory.getLog(OrdersAPI.class);

	@CrossOrigin(origins="http://localhost:3000")
	@GetMapping(value = "/{orderId}")
	public ResponseEntity<Ordersdto> getOrderDetails(@PathVariable @Min(value=1,message ="Please give orderId >=1") Long orderId) throws OrderServiceNotFoundException {
		Ordersdto orders=iOrderService.getOrderDetails(orderId);
		LOGGER.info(environment.getProperty("getOrderbyId"));
		return new ResponseEntity<>(orders, HttpStatus.OK);
	}
	@CrossOrigin(origins="http://localhost:3000")
	@GetMapping(value = "/getAllOrder")
	public ResponseEntity<List<Ordersdto>> getAllOrders() throws OrderServiceNotFoundException {
		List<Ordersdto> orderList = iOrderService.getAllOrders();
		LOGGER.info(environment.getProperty("getAllOrders"));
		return new ResponseEntity<>(orderList, HttpStatus.OK);
	}
	@CrossOrigin(origins="http://localhost:3000")
	@PostMapping(value = "/addOrder")
	public ResponseEntity<String> addOrder(@Valid @RequestBody Ordersdto order) throws OrderAlreadyExistsException {
		Ordersdto orderId = iOrderService.addOrder(order);
		String successMessage = environment.getProperty("OrderAddedSuccessfully") ;
		LOGGER.info(successMessage);
		return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
	}
	@CrossOrigin(origins="http://localhost:3000")
	@PutMapping(value = "/updateOrder/{orderId}")
	public ResponseEntity<String> updateOrder(@PathVariable @Min(value=1,
	message ="Please give orderId >=1") Long orderId, @RequestBody Ordersdto order) 
			throws OrderServiceNotFoundException {
		iOrderService.updateOrder(orderId, order) ;
		String successMessage = environment.getProperty("OrderUpdated");
		LOGGER.info(successMessage);
		return new ResponseEntity<>(successMessage, HttpStatus.OK); 
	}
	
	@CrossOrigin(origins="http://localhost:3000")
	@DeleteMapping(value = "/deleteOrder/{orderId}")
	public ResponseEntity<String> deleteOrder(@PathVariable @Min(value=1,message ="Please give orderId >=1") Long orderId) throws OrderServiceNotFoundException {
		iOrderService.deleteOrder(orderId) ;
		String successMessage = environment.getProperty("OrderDeletedSuccessfully");
		LOGGER.info(successMessage);
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
	}
	
	
}