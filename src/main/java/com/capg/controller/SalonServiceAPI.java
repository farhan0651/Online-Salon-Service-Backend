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
import com.capg.entity.SalonService;
import com.capg.exception.SalonServiceNotFoundException;
import com.capg.exception.ServiceAlreadyExistsException;
import com.capg.service.ISalonService;

@RestController
@RequestMapping(value = "/SalonService")
@Validated
public class SalonServiceAPI {

	@Autowired
	private ISalonService salonService;
	
	@Autowired
	private Environment environment;  
	
	public static final Log LOGGER=LogFactory.getLog(SalonServiceAPI.class);

	@CrossOrigin(origins="http://localhost:3000")
	@GetMapping(value = "/{serviceId}")
	public ResponseEntity<SalonService> getService(@PathVariable @Min(value=1,message ="Please give serviceId >=1") Long serviceId) throws SalonServiceNotFoundException {
		SalonService s = salonService.getService(serviceId);
		LOGGER.info(environment.getProperty("getServiebyServicId"));
		return new ResponseEntity<>(s, HttpStatus.OK);
	} 
	
	@CrossOrigin(origins="http://localhost:3000")
	@GetMapping(value = "/services")
	public ResponseEntity<List<SalonService>> getAllServices() throws SalonServiceNotFoundException {
		List<SalonService> s = salonService.getAllServices();
		LOGGER.info(environment.getProperty("getAllService"));
		return new ResponseEntity<>(s, HttpStatus.OK);
	}
	
	@CrossOrigin(origins="http://localhost:3000")
	@PostMapping(value = "/addService")
	public ResponseEntity<String> addService(@Valid @RequestBody SalonService s) throws ServiceAlreadyExistsException {
		SalonService service = salonService.addService(s);
		String successMessage = environment.getProperty("service successfully");
		LOGGER.info(successMessage);
		return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
	}

	@CrossOrigin(origins="http://localhost:3000")
	@DeleteMapping(value = "/deleteService/{serviceId}")
	public ResponseEntity<String> removeService(@PathVariable @Min(value=1,message ="Please give serviceId >=1") Long serviceId) throws SalonServiceNotFoundException {
		salonService.removeService(serviceId) ;
		String successMessage = environment.getProperty("serviceDeletedSuccessfully");
		LOGGER.info(successMessage);
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
	}
	
	@CrossOrigin(origins="http://localhost:3000")
	@PutMapping(value = "/updateService/{serviceId}")
	public ResponseEntity<String> updateService(@PathVariable @Min(value=1,message ="Please give serviceId >=1") Long serviceId, @RequestBody SalonService service)
			throws SalonServiceNotFoundException {
		salonService.updateService(serviceId, service);
		String successMessage = environment.getProperty("Updated Successfully");
		LOGGER.info(successMessage);
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
	}
	
	@CrossOrigin(origins="http://localhost:3000")
	@GetMapping(value = "/service/{serviceName}")
	public ResponseEntity<List<SalonService>> getServiceByName(@PathVariable String serviceName) throws SalonServiceNotFoundException {
		List<SalonService> s = salonService.getServiceByName(serviceName);
		LOGGER.info(environment.getProperty("getServiceByName"));
		return new ResponseEntity<>(s, HttpStatus.OK);
	} 
	
	@CrossOrigin(origins="http://localhost:3000")
	@GetMapping(value = "/service/ServicePrice/{servicePrice}")
	public ResponseEntity<List<SalonService>> getServiceByPrice(@PathVariable String servicePrice) throws SalonServiceNotFoundException {
		List<SalonService> s = salonService.getServiceByPrice(servicePrice);
		LOGGER.info(environment.getProperty("getServiceByPrice"));
		return new ResponseEntity<>(s, HttpStatus.OK);
	} 
	
	@CrossOrigin(origins="http://localhost:3000")
	@GetMapping(value = "/service/ServiceDuration/{serviceDuration}")
	public ResponseEntity<List<SalonService>> getServiceByDuration(@PathVariable String serviceDuration) throws SalonServiceNotFoundException {
		List<SalonService> s = salonService.getServicesByDuration(serviceDuration);
		LOGGER.info(environment.getProperty("getServiceByDuration"));
		return new ResponseEntity<>(s, HttpStatus.OK);
	}
}