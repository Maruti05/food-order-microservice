package com.eatza.reviewservice.authenticationservice;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.eatza.reviewservice.dto.CustomerDto;
import com.eatza.reviewservice.dto.UserDto;
import com.eatza.reviewservice.exception.UnauthorizedException;
import com.eatza.reviewservice.feignclient.CustomerClient;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtAuthenticationService {
	
	//@Value("${user}")
	String username;
	
	//@Value("${password}")
	String password;
	private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationService.class);

	
	private static final long EXPIRATION_TIME = 900000;
	
	@Autowired
	private CustomerClient customerClient;
	
	public String authenticateUser(UserDto user) throws UnauthorizedException {
		CustomerDto customer=	customerClient.getCustomer(user.getUsername(), user.getPassword());
		if(!(user.getUsername().equals(customer.getName()))) {
			logger.debug("Username is invalid");
			throw new UnauthorizedException("Invalid Credentials");
		}
		if(!(user.getPassword().equals(customer.getPassword()))){
			logger.debug("Password is invalid");
			throw new UnauthorizedException("Invalid Credentials");
			
		}
		return Jwts.builder().setSubject(customer.getName()).claim("roles", "user").setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256, "secretkey").setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)).compact();
		
		
	}

}
