package com.capg.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.dto.User1dto;
import com.capg.exception.UserException;
import com.capg.service.User1ServiceImp;

// TODO: Auto-generated Javadoc
/**
 * The Class UserController.
 */
@RestController
@RequestMapping("/user")
@Validated
public class UserAPI {
	
	/** The Iuser service. */
	@Autowired
	private User1ServiceImp IuserService;
	
	/**
	 * Sign up.
	 *
	 * @param userDTO the user DTO
	 * @return the response entity
	 * @throws UserException the user exception
	 */
	@CrossOrigin(origins="http://localhost:3000")
	@PostMapping("/register")
	public ResponseEntity<String> signUp(@RequestBody @Valid User1dto userDTO) throws UserException{
		IuserService.signUp(userDTO);
		return new ResponseEntity<String>("User registered",HttpStatus.CREATED);
	}
	
	/**
	 * Sign in.
	 *
	 * @param userDTO the user DTO
	 * @return the response entity
	 * @throws UserException the user exception
	 */
	@CrossOrigin(origins="http://localhost:3000")
	@PostMapping("/signin")
	public ResponseEntity<String> signIn(@RequestBody User1dto userDTO) throws UserException{
		IuserService.signIn(userDTO);
		return new ResponseEntity<String>("Signin Successful",HttpStatus.OK);
	}
	
	/**
	 * Change password.
	 *
	 * @param userDTO the user DTO
	 * @return the response entity
	 * @throws UserException the user exception
	 */
	@CrossOrigin(origins="http://localhost:3000")
	@PutMapping("/changePassword")
	public ResponseEntity<String> changePassword(@RequestBody User1dto userDTO) throws UserException{
		IuserService.changePassword(userDTO);
		return new ResponseEntity<String>("Password Updated",HttpStatus.OK);
	}
	
	/**
	 * Gets the user id.
	 *
	 * @param userName the user name
	 * @return the user id
	 * @throws UserException the user exception
	 */
	@CrossOrigin(origins="http://localhost:3000")
	@GetMapping("/{userName}")
	public ResponseEntity<Long> getUserId(@PathVariable String userName) throws UserException{
		User1dto userId=IuserService.getUserId(userName);
		return new ResponseEntity<Long>(userId.getUserId(),HttpStatus.OK);
	}

}