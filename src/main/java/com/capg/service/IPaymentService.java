package com.capg.service;

import java.util.List;

import com.capg.dto.Paymentdto;
import com.capg.exception.PaymentAlreadyExistsException;
import com.capg.exception.PaymentServiceNotFoundException;

public interface IPaymentService {
	
	public Paymentdto getPaymentDetails(long paymentId) throws PaymentServiceNotFoundException;
	public Paymentdto addPayment(Paymentdto payment) throws PaymentAlreadyExistsException;
	public void deletePayment(long paymentId) throws PaymentServiceNotFoundException;
	public Paymentdto updatePayment(long paymentId, Paymentdto payment) throws PaymentServiceNotFoundException;
	public List<Paymentdto> getAllPaymentDetails() throws PaymentServiceNotFoundException;
}