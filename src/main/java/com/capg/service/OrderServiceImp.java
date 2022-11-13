package com.capg.service;

import java.util.ArrayList;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capg.dto.Appointmentdto;
import com.capg.dto.Ordersdto;
import com.capg.entity.Appointment;
import com.capg.entity.Orders;
import com.capg.exception.AppointmentServiceNotFoundException;
import com.capg.exception.OrderAlreadyExistsException;
import com.capg.exception.OrderServiceNotFoundException;
import com.capg.repository.IOrderRepository;

@Service(value = "orderService")
@Transactional

public class OrderServiceImp implements IOrderService{
	
	@Autowired
	private IOrderRepository orderRepository;
	
	@Override
	public Ordersdto addOrder(Ordersdto order) throws OrderAlreadyExistsException{
		Optional<Orders> orders = orderRepository.findById(order.getOrderId());
		if(orders.isPresent())
		
			throw new OrderAlreadyExistsException("Service.ORDER_ALREADY_EXISTS");
		
		Orders order1 = Orders.DTOToentity(order);
		orderRepository.save(order1);
		return order;
		
	}
	@Override
	public void deleteOrder(Long orderId) throws OrderServiceNotFoundException{
		
		Optional<Orders> orders = orderRepository.findById(orderId);
		Orders order1 = orders.orElseThrow(() -> new OrderServiceNotFoundException("Service.Order_NOT_FOUND"));
		order1.setAppointment(null);
		orderRepository.deleteById(orderId);	
	
	}

	@Override
	public Ordersdto updateOrder(Long orderId, Ordersdto order) throws OrderServiceNotFoundException{
		Optional<Orders> order1 = orderRepository.findById(orderId);
		Orders o = order1.orElseThrow(() -> new OrderServiceNotFoundException("Service.Order_NOT_FOUND"));
		o.setPaymentMethod(order.getPaymentMethod());
		o.setAmount(order.getAmount());
		o.setBillingDate(order.getBillingDate());
		return order;
		
	}
	@Override
	public Ordersdto getOrderDetails(Long orderId) throws OrderServiceNotFoundException{
		Optional<Orders> optional = orderRepository.findById(orderId);
		Orders ord = optional.orElseThrow(() -> new OrderServiceNotFoundException("Service.Order_NOT_FOUND"));
		Ordersdto ordersdto = Ordersdto.entityToDTO(ord);
		return ordersdto;
	}
	@Override
	public List<Ordersdto> getAllOrders() throws OrderServiceNotFoundException{
		//Iterable<Orders> order2 = orderRepository.findAll(); 
		List<Orders> order = orderRepository.findAll();
		if(order.isEmpty())
			throw new OrderServiceNotFoundException("Service.Order_NOT_FOUND");
		List<Ordersdto> orders = new ArrayList<>();
		order.forEach(o -> {
			orders.add(Ordersdto.entityToDTO(o));
		});
		return orders;
	}
	
	
	
			
}