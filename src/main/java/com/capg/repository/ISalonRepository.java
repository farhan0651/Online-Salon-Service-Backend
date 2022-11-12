package com.capg.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.capg.entity.SalonService;

@Repository
public interface ISalonRepository extends JpaRepository<SalonService, Long>{
	
	public List<SalonService> findByServicePrice(String servicePrice);
    
    public List<SalonService> findByServiceName(String serviceName);
	
	public List<SalonService> findByServiceDuration(String serviceDuration);
}
