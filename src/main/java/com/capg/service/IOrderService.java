package com.capg.service;

import java.util.List;

import com.capg.dto.Ordersdto;
import com.capg.entity.Orders;
import com.capg.exception.OrderAlreadyExistsException;
import com.capg.exception.OrderServiceNotFoundException;


public interface IOrderService {
	
	public Ordersdto addOrder(Ordersdto order) throws OrderAlreadyExistsException;
	public void deleteOrder(Long orderId) throws OrderServiceNotFoundException;
	public Ordersdto updateOrder(Long orderId, Ordersdto order) throws OrderServiceNotFoundException;
	public Ordersdto getOrderDetails(Long orderId) throws OrderServiceNotFoundException;
	public List<Ordersdto> getAllOrders() throws OrderServiceNotFoundException ;
}