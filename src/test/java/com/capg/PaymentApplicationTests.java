package com.capg;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.capg.dto.Paymentdto;
import com.capg.entity.Payment;
import com.capg.exception.PaymentAlreadyExistsException;
import com.capg.exception.PaymentServiceNotFoundException;
import com.capg.repository.IPaymentRepository;
import com.capg.service.IPaymentService;
import com.capg.service.IPaymentServiceImp;

@SpringBootTest
public class PaymentApplicationTests {
	@Mock
	IPaymentRepository paymentRepository;
	
	@InjectMocks
	IPaymentService payment = new IPaymentServiceImp();
	
	public static Payment demo() {
		Payment pay = new Payment();
		pay.setPaymentId(1);
		pay.setType("Card");
		pay.setStatus("Ok");
		return pay;
	}
	//Add valid
	@Test
	void validPaymentAddition() throws PaymentServiceNotFoundException,PaymentAlreadyExistsException{
		Paymentdto paymentDTO = Paymentdto.entityToDTO(PaymentApplicationTests.demo());
		Mockito.when(paymentRepository.findById(paymentDTO.getPaymentId())).thenReturn(Optional.empty());
		Assertions.assertEquals(payment.addPayment(paymentDTO),paymentDTO);
	}
	
	//Add invalid
	@Test
	void invalidPaymentAddition() throws PaymentServiceNotFoundException,PaymentAlreadyExistsException{
		Payment payments = PaymentApplicationTests.demo();
		Mockito.when(paymentRepository.findById(payments.getPaymentId())).thenReturn(Optional.of(payments));
		PaymentAlreadyExistsException pay = Assertions.assertThrows(PaymentAlreadyExistsException.class, () -> 
		payment.addPayment(Paymentdto.entityToDTO(payments)));
		Assertions.assertEquals(pay.getMessage(),"Service.PAYMENT_ALREADY_EXISTS");
	}
	//Update valid
	@Test
	void validPaymentUpdate() throws PaymentServiceNotFoundException, PaymentAlreadyExistsException {
		Payment payments = PaymentApplicationTests.demo();
		Mockito.when(paymentRepository.findById(payments.getPaymentId())).thenReturn(Optional.of(payments));
		Assertions.assertEquals(payment.updatePayment(payments.getPaymentId(),Paymentdto.entityToDTO(payments)), Paymentdto.entityToDTO(payments));
	}
	
	//Update invalid
	@Test
	void invalidPaymentUpdate() throws PaymentServiceNotFoundException, PaymentAlreadyExistsException{
		Paymentdto paymentdto = Paymentdto.entityToDTO(PaymentApplicationTests.demo());
		Mockito.when(paymentRepository.findById(paymentdto.getPaymentId())).thenReturn(Optional.empty());
		PaymentServiceNotFoundException pay = Assertions.assertThrows(PaymentServiceNotFoundException.class, () 
				-> payment.updatePayment(paymentdto.getPaymentId(),paymentdto));
		Assertions.assertEquals(pay.getMessage(), "Service.Payment_NOT_FOUND");
	}
	
	// Delete valid
	@Test
	void validDeletePayment() throws PaymentServiceNotFoundException, PaymentAlreadyExistsException{
		Payment payments = PaymentApplicationTests.demo();
		Mockito.when(paymentRepository.findById(payments.getPaymentId())).thenReturn(Optional.of(payments));
		Assertions.assertDoesNotThrow(() -> payment.deletePayment(payments.getPaymentId()));
	}
	
	//Delete invalid
	@Test
	void invalidDeletePayment() throws PaymentServiceNotFoundException{
		Paymentdto paymentdto = Paymentdto.entityToDTO(PaymentApplicationTests.demo());
		Mockito.when(paymentRepository.findById(paymentdto.getPaymentId())).thenReturn(Optional.empty());
		PaymentServiceNotFoundException ord = Assertions.assertThrows(PaymentServiceNotFoundException.class, ()-> payment.deletePayment(paymentdto.getPaymentId()));
		Assertions.assertEquals(ord.getMessage(), "Service.Payment_NOT_FOUND");
	}
	
	//Get valid
	@Test
	void validGetPaymentDetails() throws PaymentServiceNotFoundException,PaymentAlreadyExistsException{
		Payment payments = PaymentApplicationTests.demo();
		Mockito.when(paymentRepository.findById(payments.getPaymentId())).thenReturn(Optional.of(payments));
		Assertions.assertEquals(payment.getPaymentDetails(payments.getPaymentId()), Paymentdto.entityToDTO(payments));
	}
	//Get invalid
	@Test
	void invalidGetPaymentDetails() throws PaymentServiceNotFoundException, PaymentAlreadyExistsException{
		Paymentdto paymentdto = Paymentdto.entityToDTO(PaymentApplicationTests.demo());
		Mockito.when(paymentRepository.findById(paymentdto.getPaymentId())).thenReturn(Optional.empty());
		PaymentServiceNotFoundException pay= Assertions.assertThrows(PaymentServiceNotFoundException.class, () -> payment.getPaymentDetails(paymentdto.getPaymentId()));
		Assertions.assertEquals(pay.getMessage(), "Service.Payment_NOT_FOUND");
	}
	@Test
	void validGetAllPayment() throws PaymentServiceNotFoundException, PaymentAlreadyExistsException {
		Payment payments = PaymentApplicationTests.demo();
		List<Payment> list = new ArrayList<>();
		list.add(payments);
		Mockito.when(paymentRepository.findAll()).thenReturn(list);
		List<Paymentdto> listdto = new ArrayList<>();
		list.forEach(p -> {
			listdto.add(Paymentdto.entityToDTO(p));
		});
		Assertions.assertEquals(payment.getAllPaymentDetails(), listdto);
	}
	
	@Test
	void invalidGetAllPayment() throws PaymentServiceNotFoundException, PaymentAlreadyExistsException{
		Mockito.when(paymentRepository.findAll()).thenReturn(new ArrayList<Payment>());
		PaymentServiceNotFoundException pay = Assertions.assertThrows(PaymentServiceNotFoundException.class, ()-> payment.getAllPaymentDetails());
		Assertions.assertEquals(pay.getMessage(), "Service.Payment_NOT_FOUND");
	}
	
}