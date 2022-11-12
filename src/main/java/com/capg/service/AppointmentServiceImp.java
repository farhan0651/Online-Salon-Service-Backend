package com.capg.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capg.dto.Appointmentdto;
import com.capg.entity.Appointment;
import com.capg.exception.AppointmentAlreadyExistsException;
import com.capg.exception.AppointmentServiceNotFoundException;
import com.capg.repository.IAppointmentRepository;

@Service(value="appointmentService")
@Transactional
public class AppointmentServiceImp implements IAppointmentService{
	@Autowired
	private IAppointmentRepository appointmentRepository;
	
	
	//Function for adding an appointment
	@Override
	public Appointment addAppointment(Appointmentdto appointment)throws AppointmentAlreadyExistsException {
		Optional<Appointment> appointment2 = appointmentRepository.findById(appointment.getAppointmentId());
		if(appointment2.isPresent()) {
			throw new AppointmentAlreadyExistsException("Service.APPOINTMENT_ALREADY_EXISTS");
		}
		else {
		Appointment appointmentEntity = new Appointment();
		appointmentEntity.setAppointmentId(appointment.getAppointmentId());
		appointmentEntity.setLocation(appointment.getLocation());
		appointmentEntity.setVisitType(appointment.getVisitType());
		appointmentEntity.setPreferredDate(appointment.getPreferredDate());
		appointmentEntity.setPreferredTime(appointment.getPreferredTime());
		appointmentEntity.setPayment(appointment.getPayment());
		appointmentEntity.setSalonService(appointment.getSalonService());
		appointmentEntity.setCustomer(appointment.getCustomer());
		 appointmentRepository.save(appointmentEntity);
		return appointmentEntity;
		}
	}
	
	
	//Function for removing an appointment by getting the appointment id from the user
	@Override
	public void removeAppointment(long id)throws AppointmentServiceNotFoundException {
		Optional<Appointment> appointment = appointmentRepository.findById(id);
		appointment.orElseThrow(() -> new AppointmentServiceNotFoundException("Service.APPOINTMENT_NOT_FOUND"));
		appointmentRepository.deleteById(id);
	}
	
	
	//Function for updating the location of an existing appointment
	@Override
	public void updateAppointment(long id,Appointmentdto appointments)throws AppointmentServiceNotFoundException {
		Optional<Appointment> appointment = appointmentRepository.findById(id);
		Appointment a = appointment.orElseThrow(() -> new AppointmentServiceNotFoundException("Service.APPOINTMENT_NOT_FOUND"));
		a.setLocation(appointments.getLocation());
	}
	
	
	//Function for getting an appointment by getting id from the user
	@Override
	public Appointmentdto getAppointment(Long id) throws AppointmentServiceNotFoundException{ 
		Optional<Appointment> optional = appointmentRepository.findById(id);
		Appointment appointment = optional.orElseThrow(() -> new AppointmentServiceNotFoundException("Service.APPOINTMENT_NOT_FOUND"));
		Appointmentdto appointment2 = new Appointmentdto();
		appointment2.setAppointmentId(appointment.getAppointmentId());
		appointment2.setSalonService(appointment.getSalonService());
		appointment2.setLocation(appointment.getLocation());
		appointment2.setPreferredDate(appointment.getPreferredDate());
		appointment2.setVisitType(appointment.getVisitType());
		appointment2.setCustomer(appointment.getCustomer());
		appointment2.setPayment(appointment.getPayment());
		appointment2.setPreferredTime(appointment.getPreferredTime());
		return appointment2;
	}
	
	
	//Function for getting all appointments
	@Override
	public List<Appointmentdto> getAllAppointments()throws AppointmentServiceNotFoundException {
		Iterable<Appointment> appointments = appointmentRepository.findAll(); 
		List<Appointmentdto> appointments2 = new ArrayList<>();
		appointments.forEach(appointment -> {
			Appointmentdto app = new Appointmentdto();
			app.setAppointmentId(appointment.getAppointmentId());
			app.setLocation(appointment.getLocation());
			app.setCustomer(appointment.getCustomer());
			app.setSalonService(appointment.getSalonService());
			app.setPayment(appointment.getPayment());
			app.setVisitType(appointment.getVisitType());
			app.setPreferredDate(appointment.getPreferredDate());
			app.setPreferredTime(appointment.getPreferredTime());
			appointments2.add(app);
		});
		if (appointments2.isEmpty())
			throw new AppointmentServiceNotFoundException("Service.APPOINTMENT_NOT_FOUND");
		return appointments2;
	}
	
	
	//Function for getting open appointments
	@Override
	public List<Appointmentdto> getOpenAppointments()throws AppointmentServiceNotFoundException {
		Iterable<Appointment> appointments = appointmentRepository.findAll(); 
		List<Appointmentdto> appointments2 = new ArrayList<>();
		appointments.forEach(appointment -> {
			Appointmentdto app = new Appointmentdto();
			if(appointment.getVisitType().equals("Open")) {
			app.setAppointmentId(appointment.getAppointmentId());
			app.setVisitType(appointment.getVisitType());
			app.setSalonService(appointment.getSalonService());
			app.setPreferredTime(appointment.getPreferredTime());
			app.setLocation(appointment.getLocation());
			app.setCustomer(appointment.getCustomer());
			app.setPayment(appointment.getPayment());
			app.setPreferredDate(appointment.getPreferredDate());
			app.setPreferredTime(appointment.getPreferredTime());
			appointments2.add(app);
			}
		});
		if (appointments2.isEmpty())
			throw new AppointmentServiceNotFoundException("Service.APPOINTMENT_NOT_FOUND");
		return appointments2;
	}
	
}
