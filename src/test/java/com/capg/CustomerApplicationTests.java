package com.capg;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.capg.dto.Appointmentdto;
import com.capg.dto.Customerdto;
import com.capg.entity.Appointment;
import com.capg.entity.Customer;
import com.capg.exception.AppointmentServiceNotFoundException;
import com.capg.exception.CustomerAlreadyExistsException;
import com.capg.exception.CustomerServiceNotFoundException;
import com.capg.repository.ICustomerRepository;
import com.capg.service.CustomerServiceImp;
import com.capg.service.ICustomerService;

@SpringBootTest
public class CustomerApplicationTests {

	
	@Mock
	ICustomerRepository customerRepository;
	
	@InjectMocks
	ICustomerService customerService = new CustomerServiceImp();
	
	public static Customer demo() {
		Customer c =new Customer();
		c.setCustomerId(1);
		c.setContactNo("6204803447");
		c.setEmail("princzraj0@gmail.com");
		c.setName("Prince");
		
		return c;
		
	}
	

	@Test
	void validCustomerAddition() throws CustomerAlreadyExistsException{
		Customerdto customerdto = Customerdto.entityToDTO(CustomerApplicationTests.demo());
		Mockito.when(customerRepository.findById(customerdto.getCustomerId())).thenReturn(Optional.empty());
		Assertions.assertEquals(customerService.addCustomer(customerdto), Customer.DTOToentity(customerdto)  );
	}
	
	/*@Test
	void validAppointmentAddition() throws AppointmentServiceNotFoundException{
		Appointmentdto appointmentdto = Appointmentdto.entityToDTO(AppointmentApplicationTests.demo());
		Mockito.when(appointmentRepository.findById(appointmentdto.getAppointmentId())).thenReturn(Optional.empty());
		Assertions.assertEquals(appointmentService.addAppointment(appointmentdto), Appointment.DTOtoentity(appointmentdto)  );
	}*/
}
