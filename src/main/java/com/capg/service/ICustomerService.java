package com.capg.service;


import java.util.List;

import com.capg.dto.Customerdto;
import com.capg.entity.Customer;
import com.capg.exception.CustomerAlreadyExistsException;
import com.capg.exception.CustomerServiceNotFoundException;

public interface ICustomerService {

	public Customerdto getCustomer(Integer userId) throws CustomerServiceNotFoundException;
	public Customerdto addCustomer(Customerdto customer) throws CustomerAlreadyExistsException;
	public Customer updateCustomer(Integer userId, Customerdto customer) throws CustomerServiceNotFoundException;
	public void deleteCustomer(Integer userId) throws CustomerServiceNotFoundException;
	public List<Customerdto> getAllCustomers() throws CustomerServiceNotFoundException;

}
