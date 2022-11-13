package com.capg.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.capg.dto.Customerdto;
import com.capg.dto.Ordersdto;
import com.capg.entity.Customer;
import com.capg.entity.Orders;
import com.capg.exception.CustomerAlreadyExistsException;
import com.capg.exception.CustomerServiceNotFoundException;
import com.capg.exception.OrderAlreadyExistsException;
import com.capg.exception.OrderServiceNotFoundException;
import com.capg.repository.ICustomerRepository;



@Service(value = "customerService")
@Transactional
public class CustomerServiceImp implements ICustomerService{
	
	@Autowired
	private ICustomerRepository customerRepository;
	
	//Get Customer
	@Override
	public Customerdto getCustomer(Integer customerId) throws CustomerServiceNotFoundException{ 
		Optional<Customer> optional = customerRepository.findById(customerId);
		Customer customer = optional.orElseThrow(() -> new CustomerServiceNotFoundException("Service.CUSTOMER_NOT_FOUND"));
		Customerdto customer2 = Customerdto.entityToDTO(customer);
		return customer2;
		
	}
	
	
	//Add Customer
	@Override
	public Customerdto addCustomer(Customerdto customer) throws CustomerAlreadyExistsException {
		/*Optional<Customer> customers = customerRepository.findById(customer.getCustomerId());
		if(customers.isPresent()) {
			throw new CustomerAlreadyExistsException("Service.CUSTOMER_ALREADY_EXISTS");
				}
		*/
		   Customer customer1 = Customer.DTOToentity(customer);
		   customerRepository.save(customer1);
		   return customer;
		   	
	}
	
	
	
	//Update Customer
	@Override
	public Customer updateCustomer(Integer userId, Customerdto customer) throws CustomerServiceNotFoundException {
		Optional<Customer> customers = customerRepository.findById(userId);
		Customer c = customers.orElseThrow(() -> new CustomerServiceNotFoundException("Service.CUSTOMER_NOT_FOUND"));
		c.setContactNo(customer.getContactNo());
		return c;
		
		
	}

	//Delete Customer
	@Override
	public void deleteCustomer(Integer userId) throws CustomerServiceNotFoundException {
		Optional<Customer> customer = customerRepository.findById(userId);
		customer.orElseThrow(() -> new CustomerServiceNotFoundException("Service.CUSTOMER_NOT_FOUND"));
		customerRepository.deleteById(userId);
	}

	//Get All Customer
	@Override
	public List<Customerdto> getAllCustomers() throws CustomerServiceNotFoundException {
		Iterable<Customer> customers = customerRepository.findAll(); 
		List<Customerdto> customers2 = new ArrayList<>();
		customers.forEach(customer -> {
			Customerdto cust = new Customerdto();
			cust.setCustomerId(customer.getCustomerId());
			cust.setName(customer.getName());
			cust.setContactNo(customer.getContactNo());
			cust.setEmail(customer.getEmail());
			cust.setDob(customer.getDob());
			cust.setUser1(customer.getUser1());
			cust.setAddress(customer.getAddress());
			customers2.add(cust);
		});
		if (customers2.isEmpty())
			throw new CustomerServiceNotFoundException("Service.CUSTOMERS_NOT_FOUND");
		return customers2;
	}
}
