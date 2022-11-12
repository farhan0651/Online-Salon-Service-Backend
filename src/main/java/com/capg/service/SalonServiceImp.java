package com.capg.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capg.dto.SalonServicedto;
import com.capg.entity.Orders;
import com.capg.entity.SalonService;
import com.capg.exception.OrderServiceNotFoundException;
import com.capg.exception.SalonServiceNotFoundException;
import com.capg.exception.ServiceAlreadyExistsException;
import com.capg.repository.ISalonRepository;

@Service(value = "SalonService")
@Transactional

public class SalonServiceImp implements ISalonService{


	
	@Autowired
	private ISalonRepository salonRepository;
	
    @Override
	public SalonService addService(SalonService salonService) throws ServiceAlreadyExistsException{
    	Optional<SalonService> salon = salonRepository.findById(salonService.getServiceId());
    	if(salon.isPresent()) {
    		throw new ServiceAlreadyExistsException("Service.SERVICE_ALREADY_EXISTS");
    	}
		salonRepository.save(salonService);
		return salonService;
		
	}

	
	@Override
	public void removeService(Long serviceId) throws SalonServiceNotFoundException{
		Optional<SalonService> salon = salonRepository.findById(serviceId);
		salon.orElseThrow(() -> new SalonServiceNotFoundException("Service.SalonService_NOT_FOUND"));
		salonRepository.deleteById(serviceId);
		
	}
	
	@Override
    public SalonService updateService(Long serviceId, SalonService salonService) throws SalonServiceNotFoundException{
		Optional<SalonService> salon = salonRepository.findById(serviceId);
		salonService = salon.orElseThrow(() -> new SalonServiceNotFoundException("Service.SalonService_NOT_FOUND"));
		salonService.setServiceName(salonService.getServiceName());
       return salonService;
	}
	
    @Override
	public SalonService getService(Long serviceId) throws SalonServiceNotFoundException{
		Optional<SalonService> optional = salonRepository.findById(serviceId);
		SalonService s = optional.orElseThrow(() -> new SalonServiceNotFoundException("Service.SalonService_NOT_FOUND"));
		return s;
		
	}

    @Override
	public List<SalonService> getAllServices() throws SalonServiceNotFoundException{
		
		Iterable<SalonService> order2 = salonRepository.findAll(); 
		List<SalonService> s3 = new ArrayList<>();
		order2.forEach(order -> {
			s3.add(order);
		});
		if (s3.isEmpty())
			throw new SalonServiceNotFoundException("Service.SalonService_NOT_FOUND");
		return s3;
	}
	@Override
	public List<SalonService> getServiceByName(String serviceANme) throws SalonServiceNotFoundException{
		Iterable<SalonService> order2 = salonRepository.findByServiceName(serviceANme); 
		List<SalonService> s3 = new ArrayList<>();
		order2.forEach(order -> {
			s3.add(order);
		});
		if (s3.isEmpty())
			throw new SalonServiceNotFoundException("Service.SalonService_NOT_FOUND");
		return s3;
	}

    @Override
	public List<SalonService> getServiceByPrice(String servicePrice) throws SalonServiceNotFoundException{
		Iterable<SalonService> order2 = salonRepository.findByServicePrice(servicePrice); 
		List<SalonService> s3 = new ArrayList<>();
		order2.forEach(order -> {
			s3.add(order);
		});
		if (s3.isEmpty())
			throw new SalonServiceNotFoundException("Service.SalonService_NOT_FOUND");
		return s3;
	}
 @Override
	public List<SalonService> getServicesByDuration(String serviceDuration) throws SalonServiceNotFoundException{
		Iterable<SalonService> order2 = salonRepository.findByServiceDuration(serviceDuration);
		List<SalonService> s3 = new ArrayList<>();
		order2.forEach(order -> {
			s3.add(order);
		});
		if (s3.isEmpty())
			throw new SalonServiceNotFoundException("Service.SalonService_NOT_FOUND");
		return s3;
	}


}