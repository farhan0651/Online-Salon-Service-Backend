package com.capg.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capg.entity.Appointment;


@Repository
public interface IAppointmentRepository extends JpaRepository<Appointment, Long>{
	

}
