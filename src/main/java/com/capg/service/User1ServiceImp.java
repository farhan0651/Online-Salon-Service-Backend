package com.capg.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.dto.User1dto;
import com.capg.entity.User1;
import com.capg.exception.UserException;
import com.capg.repository.IUser1Repository;

@Service
@Transactional
public class User1ServiceImp implements IUser1Service {
	
	/** The user repository. */
	@Autowired
	private IUser1Repository userRepository;
	

	//for signing up procedure

	/**
	 * Sign up.
	 *
	 * @param user the user
	 * @return the user DTO
	 * @throws UserException the user exception
	 */

	@Override
	public User1dto signUp(User1dto user) throws UserException {
		User1 u=user.toUser();
		User1 saveU=userRepository.save(u);
		return saveU.toUserDTO();
	}
	

	//for signing in

	/**
	 * Sign in.
	 *
	 * @param user the user
	 * @return the user DTO
	 * @throws UserException the user exception
	 */
	@Override
	public User1dto signIn(User1dto user) throws UserException{
		String userName=user.getUserName();
		Optional<User1> repUser=userRepository.findByUserName(user.getUserName());
		// check if user with username exists
		User1 u = repUser.orElseThrow(()-> new UserException("User.UserName_Dosenot_Exesist"));
		// throw error if password do not match
		if (!u.getPassword().equals(user.getPassword())) {
			throw new UserException("User.Wrong_Password");
		}
		return user;
	}
	

	//for signing out

	/**
	 * Sign out.
	 *
	 * @param user the user
	 * @return the user DTO
	 */

	@Override
	public User1dto signOut(User1dto user) {
		return user;
		
	}
	

	//for changing the password

	/**
	 * Change password.
	 *
	 * @param user the user
	 * @return the user DTO
	 * @throws UserException the user exception
	 */

	@Override                             
	public User1dto changePassword(User1dto user )throws UserException{
		long userId=user.getUserId();
		Optional<User1> repUser=userRepository.findById(user.getUserId());
		// check if user exists
		User1 u=repUser.orElseThrow(()-> new UserException("User.User_Not_Found"));
		//  change password
		u.setPassword(user.getPassword());
		return u.toUserDTO();
		
	}
	

	//getting user details using user name

	/**
	 * Gets the user id.
	 *
	 * @param userName the user name
	 * @return the user id
	 * @throws UserException the user exception
	 */

	@Override
	public User1dto getUserId(String userName) throws UserException {
		Optional<User1> repUser=userRepository.finduserIdByUserName(userName);
		// check if user exists
		User1 u=repUser.orElseThrow(()->new UserException("User.UserName_Doesnot_Exesist"));
		return u.toUserDTO();
	}
}
