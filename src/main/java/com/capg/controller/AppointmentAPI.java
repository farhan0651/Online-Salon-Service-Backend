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
import com.capg.dto.Appointmentdto;
import com.capg.entity.Appointment;
import com.capg.exception.AppointmentAlreadyExistsException;
import com.capg.exception.AppointmentServiceNotFoundException;
import com.capg.service.IAppointmentService;

@RestController
@RequestMapping(value="/appointment")
@Validated
public class AppointmentAPI {
@Autowired
private IAppointmentService iappointmentService;
@Autowired
private Environment environment;

public static final Log LOGGER=LogFactory.getLog(AppointmentAPI.class);

@CrossOrigin(origins="http://localhost:3000")
@GetMapping(value = "/{id}")
public ResponseEntity<Appointmentdto> getAppointment(@PathVariable @Min(value=1,message ="Please give AppointmentId >=1") Long id) throws AppointmentServiceNotFoundException{
	Appointmentdto appointment = iappointmentService.getAppointment(id);
	LOGGER.info(environment.getProperty("getAppintmentId"));

	return new ResponseEntity<>(appointment, HttpStatus.OK);
}

@CrossOrigin(origins="http://localhost:3000")
@GetMapping(value = "/getAll")
public ResponseEntity<List<Appointmentdto>> getAllAppointments() throws AppointmentServiceNotFoundException {
	List<Appointmentdto> appointmentList = iappointmentService.getAllAppointments();
	LOGGER.info(environment.getProperty("getAllAppointment"));
   return new ResponseEntity<>(appointmentList, HttpStatus.OK);
}

@CrossOrigin(origins="http://localhost:3000")
@GetMapping(value = "/getOpenAppointments")
public ResponseEntity<List<Appointmentdto>> getOpenAppointments()throws AppointmentServiceNotFoundException {
	List<Appointmentdto> appointmentList = iappointmentService.getOpenAppointments();
	LOGGER.info(environment.getProperty("getOpenAppointment"));
	return new ResponseEntity<>(appointmentList, HttpStatus.OK);
}



@CrossOrigin(origins="http://localhost:3000")
@PostMapping(value = "/addAppointment")
public ResponseEntity<String> addCustomer(@Valid @RequestBody Appointmentdto appoitntmetDTO) throws AppointmentAlreadyExistsException {
	Appointment app = iappointmentService.addAppointment(appoitntmetDTO);
	String successMessage = environment.getProperty("appointment added -") + app;
	LOGGER.info(successMessage);
	return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
}

@CrossOrigin(origins="http://localhost:3000")
@PutMapping(value = "/updateAppointment/{id}")
public ResponseEntity<String> updateCustomer(@PathVariable @Min(value=1,message ="Please give AppointmentId >=1") Long id , @RequestBody Appointmentdto appointment)
		throws AppointmentServiceNotFoundException {
	iappointmentService.updateAppointment(id, appointment);
	String successMessage = environment.getProperty("AppointmentUpdatedSuccessfully");
	LOGGER.info(successMessage);
	return new ResponseEntity<>(successMessage, HttpStatus.OK);
}

@CrossOrigin(origins="http://localhost:3000")
@DeleteMapping(value = "/deleteAppointment/{id}")
public ResponseEntity<String> removeAppointment(@PathVariable @Min(value=1,message ="Please give AppointmentId >=1") Long id)throws AppointmentServiceNotFoundException {
	iappointmentService.removeAppointment(id);
	String successMessage = environment.getProperty("ApideletedSuccessfully");
	LOGGER.info(successMessage);
	return new ResponseEntity<>(successMessage, HttpStatus.OK);
} 

}
