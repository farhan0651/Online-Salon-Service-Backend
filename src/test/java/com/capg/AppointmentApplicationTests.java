package com.capg;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.capg.dto.Appointmentdto;
import com.capg.entity.Appointment;
import com.capg.exception.AppointmentAlreadyExistsException;
import com.capg.exception.AppointmentServiceNotFoundException;
import com.capg.repository.IAppointmentRepository;
import com.capg.service.AppointmentServiceImp;
import com.capg.service.IAppointmentService;

@SpringBootTest
public class AppointmentApplicationTests {

	@Mock
	IAppointmentRepository appointmentRepository;
	
	@InjectMocks
	IAppointmentService appointmentService = new AppointmentServiceImp();
	
	public static Appointment demo() {
		Appointment a = new Appointment();
		a.setAppointmentId(1);
		a.setLocation("");
		a.setVisitType("");
		//a.setPreferredDate();
		
		return a;
	}
	
	@Test
	void validAppointmentAddition() throws AppointmentAlreadyExistsException{
		Appointmentdto appointmentdto = Appointmentdto.entityToDTO(AppointmentApplicationTests.demo());
		Mockito.when(appointmentRepository.findById(appointmentdto.getAppointmentId())).thenReturn(Optional.empty());
		Assertions.assertEquals(appointmentService.addAppointment(appointmentdto), Appointment.DTOtoentity(appointmentdto)  );
	}
	
	
}
