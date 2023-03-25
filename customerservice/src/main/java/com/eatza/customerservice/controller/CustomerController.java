package com.eatza.customerservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eatza.customerservice.exception.CustomerNotFoundException;
import com.eatza.customerservice.model.Customer;
import com.eatza.customerservice.service.customerservice.CustomerService;

@RestController
@RequestMapping(value = "/customer/api/v1")
public class CustomerController {
	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping(value = "/add-customer",produces =MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addCustomer(@RequestBody Customer customer) {
		logger.debug("In add customer method, calling service");
		customerService.addCustomer(customer);
		return  ResponseEntity.status(HttpStatus.OK).body("Customer Added successfully");
		
	}
	@PutMapping("/update-customer")
	public ResponseEntity<?> updateCustomer(@RequestBody Customer customer) throws CustomerNotFoundException {
		logger.debug("In add customer method, calling service");
		customerService.updateCustomer(customer);
		return  ResponseEntity.status(HttpStatus.OK).body("Customer updated successfully");
		
	}
	@DeleteMapping("/delete-customer/{id}")
	public ResponseEntity<?> deactvateCustomer(@PathVariable Long id) throws CustomerNotFoundException {
		logger.debug("In add customer method, calling service");
		customerService.deactivateCustomer(id);
		return  ResponseEntity.status(HttpStatus.OK).body("Customer deleted successfully");
		
	}
	@GetMapping(value = "/get-customer",params = {"username","password"})
	public ResponseEntity<?> getCustomer(@RequestParam String username,@RequestParam String password) throws CustomerNotFoundException {
		logger.debug("In get customer method, calling service");
	    Customer customer=customerService.getCustomer(username,password);
		return  ResponseEntity.status(HttpStatus.OK).body(customer);
		
	}
	@GetMapping(value = "/get-customer-by-id",params = {"id"},produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> getCustomerById(@RequestParam Long id) throws CustomerNotFoundException {
		logger.debug("In get customer By id method, calling service");
	    Customer customer=customerService.getCustomerById(id);
		return  ResponseEntity.status(HttpStatus.OK).body(customer);
		
	}
}
