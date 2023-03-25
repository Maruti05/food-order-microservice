package com.eatza.customerservice.service.customerservice;

import com.eatza.customerservice.exception.CustomerNotFoundException;
import com.eatza.customerservice.model.Customer;

public interface CustomerService {

	Customer addCustomer(Customer customer);
    Customer updateCustomer(Customer customer) throws CustomerNotFoundException;
    void deactivateCustomer(Long id) throws CustomerNotFoundException;
	Customer getCustomer(String username, String password) throws CustomerNotFoundException;
	Customer getCustomerById(Long id) throws CustomerNotFoundException;
}
