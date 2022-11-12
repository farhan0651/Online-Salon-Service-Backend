package com.capg.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capg.entity.User1;

@Repository
public interface IUser1Repository extends JpaRepository< User1, Long>{

	//Query to select the User by using userName
	@Query("Select U from User1 U where U.userName = ?1")
	Optional<User1> finduserIdByUserName(String userName);
	Optional<User1> findByUserName(String userName);

}