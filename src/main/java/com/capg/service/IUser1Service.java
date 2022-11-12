package com.capg.service;

import com.capg.dto.User1dto;
import com.capg.exception.UserException;

public interface IUser1Service {
		/**
		 * Sign up.
		 *
		 * @param user the user
		 * @return the user DTO
		 * @throws UserException the user exception
		 */
		public User1dto signUp(User1dto user)throws UserException;
		
		/**
		 * Sign in.
		 *
		 * @param user the user
		 * @return the user DTO
		 * @throws UserException the user exception
		 */
		public User1dto signIn(User1dto user) throws UserException;
		
		/**
		 * Sign out.
		 *
		 * @param user the user
		 * @return the user DTO
		 */
		public User1dto signOut(User1dto user);
		
		/**
		 * Change password.
		 *
		 * @param user the user
		 * @return the user DTO
		 * @throws UserException the user exception
		 */
		public User1dto changePassword(User1dto user )throws UserException;
		
		/**
		 * Gets the user id.
		 *
		 * @param userName the user name
		 * @return the user id
		 * @throws UserException the user exception
		 */
		public User1dto getUserId(String userName)throws UserException;

	}

