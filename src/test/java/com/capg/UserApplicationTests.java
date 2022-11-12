package com.capg;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import java.util.Optional;
//import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.capg.dto.User1dto;
import com.capg.entity.User1;
import com.capg.exception.UserException;
import com.capg.repository.IUser1Repository;
import com.capg.service.IUser1Service;
import com.capg.service.User1ServiceImp;

@SpringBootTest
public class UserApplicationTests {
		@Mock
		private IUser1Repository userRepository;
		@InjectMocks
		private IUser1Service userService= new User1ServiceImp();
		
		User1 userR;
		
//		@BeforeEach
//		public void steup() {
//			user=new User();
//			user.setUserId(1);
//			user.setUserName("teja@gmail.com");
//			user.setPassword("teja@123");
//			user.setRole("customer");
//			MockitoAnnotations.initMocks(this);
//		}
		@Test
		public void signUpSuccessfull() throws UserException{
			userR=new User1();
			userR.setUserId(1);
			userR.setUserName("teja@gmail.com");
			userR.setPassword("teja@123");
			
			
			User1dto user=new User1dto();
			user.setUserId(1);
			user.setUserName("teja@gmail.com");
			user.setPassword("teja@123");
			
			
			Mockito.when(userRepository.save(ArgumentMatchers.any(User1.class))).thenReturn(user.toUser());
			User1dto actual=userService.signUp(user);
			Assertions.assertEquals(userR, actual.toUser());
		}
		@Test
		public void signInFailureWrongUserName() throws UserException{
			userR=new User1();
			userR.setUserId(1);
			userR.setUserName("teja@gmail.com");
			userR.setPassword("teja@123");
			
			User1dto user=new User1dto();
			user.setUserId(1);
			user.setUserName("eja@gmail.com");
			user.setPassword("teja@123");
			
			Mockito.when(userRepository.finduserIdByUserName("teja@gmail.com")).thenReturn(Optional.empty());
			UserException exception=Assertions.assertThrows(UserException.class, ()->userService.signIn(user));
			Assertions.assertEquals("User.UserName_Doesnot_Exesist", exception.getMessage());
		}
		@Test
		public void signInFailureWrongPassword() throws UserException{
			userR=new User1();
			userR.setUserId(1);
			userR.setUserName("teja@gmail.com");
			userR.setPassword("teja@123");
			
			
			User1dto user=new User1dto();
			user.setUserId(1);
			user.setUserName("teja@gmail.com");
			user.setPassword("teja@12");
			
			Mockito.when(userRepository.finduserIdByUserName("teja@gmail.com")).thenReturn(Optional.of(userR));
			UserException exception=Assertions.assertThrows(UserException.class, ()->userService.signIn(user));
			Assertions.assertEquals("User.Wrong_Password", exception.getMessage());
		}
		@Test
		public void changePasswordSuccessfull()throws UserException{
			userR=new User1();
			//userR.setUserId(1);
			userR.setUserName("teja@gmail.com");
			userR.setPassword("teja@123");
			//userR.setRole("customer");
			
			
			User1dto user=new User1dto();
			//user.setUserId(1);
			user.setUserName("teja@gmail.com");
			user.setPassword("teja@1234");
			//user.setRole("customer");
			Mockito.when(userRepository.finduserIdByUserName("teja@gmail.com")).thenReturn(Optional.of(userR));
			User1dto actual=userService.changePassword(user);
			Assertions.assertEquals(userR, actual.toUser());
		}
		/*@Test
		public void changePasswordFailure()throws UserException{
			userR=new User1();
			//userR.setUserId(1);
			userR.setUserName("teja@gmail.com");
			userR.setPassword("teja@123");
			//userR.setRole("customer");
			
			User1dto user=new User1dto();
			//user.setUserId(1);
			user.setUserName("eja@gmail.com");
			user.setPassword("teja@123");
			//user.setRole("customer");
			Mockito.when(userRepository.finduserIdByUserName("teja@gmail.com")).thenReturn(Optional.of(userR));
			UserException exception=Assertions.assertThrows(UserException.class, ()->userService.changePassword(user));
			Assertions.assertEquals("User.User_Not_Found", exception.getMessage());
		}*/
		@Test
		public void getUSerIdSuccess() throws UserException{
			userR=new User1();
			userR.setUserId(1);
			userR.setUserName("teja@gmail.com");
			
			
			User1dto user=new User1dto();
			user.setUserId(1);
			user.setUserName("eja@gmail.com");
			user.setPassword("teja@123");
			
			
			Mockito.when(userRepository.finduserIdByUserName("teja@gmail.com")).thenReturn(Optional.of(userR));
			User1dto actual=userService.getUserId("teja@gmail.com");
			Assertions.assertEquals(userR, actual.toUser());
		}
		@Test
		public void getUSerIdFailure() throws UserException{
			userR=new User1();
			userR.setUserId(1);
			userR.setUserName("teja@gmail.com");
			
			
			User1dto user=new User1dto();
			user.setUserId(1);
			user.setUserName("eja@gmail.com");
			user.setPassword("teja@123");
			Mockito.when(userRepository.finduserIdByUserName("teja@gmail.com")).thenReturn(Optional.empty());
			UserException exception=Assertions.assertThrows(UserException.class, ()->userService.getUserId("teja@gmail.com"));
			Assertions.assertEquals("User.UserName_Doesnot_Exesist", exception.getMessage());
		}

	}