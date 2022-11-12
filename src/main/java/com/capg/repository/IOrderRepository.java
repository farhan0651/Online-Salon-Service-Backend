package com.capg.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capg.entity.Orders;

@Repository
public interface IOrderRepository extends JpaRepository<Orders, Long>{

}
