package com.capg.service;

import com.capg.dto.Appointmentdto;
import com.capg.entity.Appointment;
import com.capg.exception.AppointmentAlreadyExistsException;
import com.capg.exception.AppointmentServiceNotFoundException;

import java.util.List;

//import org.springframework.data.jpa.repository.JpaRepository;

public interface IAppointmentService{
	public Appointment addAppointment(Appointmentdto appointment)throws AppointmentAlreadyExistsException;
	public void removeAppointment(long id)throws AppointmentServiceNotFoundException;
	public void updateAppointment(long id,Appointmentdto appointments)throws AppointmentServiceNotFoundException;
	public Appointmentdto getAppointment(Long id)throws AppointmentServiceNotFoundException;
	public List<Appointmentdto> getAllAppointments()throws AppointmentServiceNotFoundException;
	public List<Appointmentdto> getOpenAppointments()throws AppointmentServiceNotFoundException;;
	
}