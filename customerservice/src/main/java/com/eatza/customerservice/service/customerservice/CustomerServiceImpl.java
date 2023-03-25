package com.eatza.customerservice.service.customerservice;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eatza.customerservice.exception.CustomerNotFoundException;
import com.eatza.customerservice.model.Customer;
import com.eatza.customerservice.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService{
	private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
	
	@Autowired
	private CustomerRepository customerRepository;
	
	

	@Override
	public Customer addCustomer(Customer customer) {
		logger.debug("In add Customer, creating object of Customer to save");
		Customer newCustomer = new Customer(customer.getName(), customer.getEmail(), customer.getPhoneNumber());
		logger.debug("calling repository save customer method");
		Customer savedCustomer = customerRepository.save(newCustomer);
		return savedCustomer;
	}

	@Override
	public Customer updateCustomer(Customer customer) throws CustomerNotFoundException {
		logger.debug("In update Customer, creating object of Customer to update");
		Customer oldCustomer = customerRepository.findById(customer.getId()).orElseThrow(()->new CustomerNotFoundException("Customer not found with ID: "+customer.getId()));
		oldCustomer.setEmail(customer.getEmail());
		oldCustomer.setPhoneNumber(customer.getPhoneNumber());
		logger.debug("calling repository save customer method");
		Customer savedCustomer = customerRepository.save(oldCustomer);
		return savedCustomer;
	}
	
	@Override
	public void deactivateCustomer(Long id) throws CustomerNotFoundException {
		logger.debug("In deactivate Customer, getting object of Customer to deactivate");
		Customer oldCustomer = customerRepository.findById(id).orElseThrow(()->new CustomerNotFoundException("Customer not found with ID: "+id));
		customerRepository.deleteById(id);
		logger.debug("Customer got deleted");
	}

	@Override
	public Customer getCustomer(String username, String password) throws CustomerNotFoundException {
		logger.debug("In get Custmer method , calling repository");
		Optional<Customer> customer = customerRepository.findByNameAndPassword(username, password);
		if(!customer.isPresent())
			throw new CustomerNotFoundException("Customer not found with username: "+username);
		return customer.get();
	}

	@Override
	public Customer getCustomerById(Long id) throws CustomerNotFoundException {
		Customer customer = customerRepository.findById(id).orElseThrow(()->new CustomerNotFoundException("Customer not found with ID: "+id));
		return customer;
	}

}
