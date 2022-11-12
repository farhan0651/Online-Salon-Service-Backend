package com.capg.utility;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
	
	public static final Log LOGGER = LogFactory.getLog(LoggingAspect.class);
	
	@AfterThrowing(pointcut = "execution(* com.capg.service.*Impl.*(..))", throwing = "exception" )
	public void logFromService(Exception exception) {
		LOGGER.error(exception);
	}
	
	@AfterThrowing(pointcut = "execution(* com.capg.repository.*Impl.*(..))", throwing = "exception")
	public void logFromRepository(Exception exception) {
		LOGGER.error(exception);
	}
}


