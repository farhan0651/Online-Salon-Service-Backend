package com.capg.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.dto.Paymentdto;
import com.capg.entity.Payment;
import com.capg.exception.PaymentAlreadyExistsException;
import com.capg.exception.PaymentServiceNotFoundException;
import com.capg.repository.IPaymentRepository;


@Service(value = "paymentService")
@Transactional

public class IPaymentServiceImp implements IPaymentService {
	
	@Autowired
	private IPaymentRepository iPaymentRepository;
	
	//Get Payment Info
		@Override
	   public Paymentdto getPaymentDetails(long paymentId) throws PaymentServiceNotFoundException{ 
			Optional<Payment> optional = iPaymentRepository.findById(paymentId);
			Payment payment  = optional.orElseThrow(() -> new PaymentServiceNotFoundException("Service.Payment_NOT_FOUND"));
			Paymentdto paymentdto = Paymentdto.entityToDTO(payment);
			return paymentdto;
		}

		//Add Payment Details
		@Override
		public Paymentdto addPayment(Paymentdto payment) throws PaymentAlreadyExistsException{
			Optional<Payment> payments = iPaymentRepository.findById(payment.getPaymentId());
			if(payments.isPresent())
			{
				throw new PaymentAlreadyExistsException("Service.PAYMENT_ALREADY_EXISTS");
			}
			Payment payment1 = Payment.DTOToentity(payment);
			iPaymentRepository.save(payment1);
			return payment;
			
		}
		
		//Update Payment Information
		@Override
		public Paymentdto updatePayment(long paymentId, Paymentdto payment) throws PaymentServiceNotFoundException{
			Optional<Payment> payment1 = iPaymentRepository.findById(paymentId);
			Payment p = payment1.orElseThrow(() -> new PaymentServiceNotFoundException("Service.Payment_NOT_FOUND"));
			p.setStatus(payment.getStatus());
			p.setType(payment.getType());
			return payment;
		}


		//Delete Payment Information
		@Override
		public void deletePayment(long paymentId) throws PaymentServiceNotFoundException{
			Optional<Payment> payment1 = iPaymentRepository.findById(paymentId);
			Payment p = payment1.orElseThrow(() -> new PaymentServiceNotFoundException("Service.Payment_NOT_FOUND"));
			iPaymentRepository.deleteById(paymentId);
		}
		
		
		//GetAll payment Details
		public List<Paymentdto> getAllPaymentDetails() throws PaymentServiceNotFoundException{
			
			List<Payment> payment = iPaymentRepository.findAll();
			if(payment.isEmpty())
				throw new PaymentServiceNotFoundException("Service.Payment_NOT_FOUND");
			List<Paymentdto> payments = new ArrayList<>();
			payment.forEach(p -> {
				payments.add(Paymentdto.entityToDTO(p));
			});
			return payments;
		}
		
}