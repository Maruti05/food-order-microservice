package com.eatza.reviewservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import feign.FeignException;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<Object> exception(UnauthorizedException exception) {
		
		 return new ResponseEntity<>("Invalid Credentials", HttpStatus.UNAUTHORIZED);
	}
	@ExceptionHandler(FeignException.class)
	ResponseEntity<Object> exception(FeignException exception) {
		
		 return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(RestaurantNotFoundException.class)
	ResponseEntity<Object> exception(RestaurantNotFoundException exception) {
		
		 return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(ReviewNotFoundException.class)
	ResponseEntity<Object> exception(ReviewNotFoundException exception) {
		
		 return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(CustomerNotFoundException.class)
	ResponseEntity<Object> exception(CustomerNotFoundException exception) {
		
		 return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(InvalidTokenException.class)
	ResponseEntity<Object> exception(InvalidTokenException exception) {
		
		 return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(ServerNotReachable.class)
	ResponseEntity<Object> exception(ServerNotReachable exception) {
		
		 return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
