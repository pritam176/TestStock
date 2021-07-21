/**
 * 
 */
package com.mmt.config.exception;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author pkumar
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(SQLException.class)
	public String handleSQLException(HttpServletRequest request, Exception ex,Model m){
		logger.info("SQLException Occured:: URL="+request.getRequestURL());
		m.addAttribute("msg", ex.getMessage());
		return "error.tiles";
	}
	
	@ExceptionHandler(Exception.class)
	public String handleException(HttpServletRequest request, Exception ex,Model m){
		logger.info("SQLException Occured:: URL="+request.getRequestURL());
		m.addAttribute("msg", ex.getMessage());
		return "error.tiles";
	}
	
	@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="IOException occured")
	@ExceptionHandler(IOException.class)
	public void handleIOException(){
		logger.error("IOException handler executed");
		System.out.println("IOException handler executed");
		//returning 404 error code
	}

}
