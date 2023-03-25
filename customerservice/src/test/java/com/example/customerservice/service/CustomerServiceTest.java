package com.example.customerservice.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.eatza.customerservice.exception.CustomerNotFoundException;
import com.eatza.customerservice.model.Customer;
import com.eatza.customerservice.repository.CustomerRepository;
import com.eatza.customerservice.service.customerservice.CustomerService;
import com.eatza.customerservice.service.customerservice.CustomerServiceImpl;
@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
	Customer customer = mock(Customer.class);
	@InjectMocks
	private CustomerService customerService=new CustomerServiceImpl();
	
	@Mock
	private CustomerRepository customerRepository;
	
	private static Customer cust =null;
	
	@BeforeAll
	public static void getCustomer() {
		 cust=new Customer();
		cust.setId(12L);
		cust.setEmail("abc@gmail.com");
		cust.setName("Xyz");
		cust.setPhoneNumber("457963155");
		cust.setPassword("1234");
	}
	
	@DisplayName("Add User To repo")
	@Test
	public void addCustomer() {
		when(customerRepository.save(any(Customer.class))).thenReturn(cust);
		Customer savedCustomer = customerService.addCustomer(cust);
		assertNotNull(savedCustomer);
		assertEquals("Xyz", savedCustomer.getName());
		assertEquals("457963155", savedCustomer.getPhoneNumber());
	}
	
	@Test
	@DisplayName("Update Customer")
	public void updateCustomer() throws CustomerNotFoundException {
		when(customerRepository.findById(anyLong())).thenReturn(Optional.of(cust));
		when(customerRepository.save(any(Customer.class))).thenReturn(cust);
		assertThat(customerService.updateCustomer(cust)).isNotNull().isEqualTo(cust);
	}
	@Test
	@DisplayName("Update Customer With Exception")
	public void updateCustomerWithEx() {
		CustomerNotFoundException thrown=Assertions.assertThrows(CustomerNotFoundException.class, ()->customerService.updateCustomer(cust));
		assertTrue(thrown.getMessage().contains("Customer not found with ID: "));
	}
	
	@Test
	@DisplayName("Delete Customer")
	public void deactivateCustomer() throws CustomerNotFoundException {
		when(customerRepository.findById(anyLong())).thenReturn(Optional.of(cust));
		customerService.deactivateCustomer(cust.getId());
		verify(customerRepository,atLeastOnce()).deleteById(cust.getId());
	}
	@Test
	@DisplayName("Delete Customer With Exception")
	public void deleteCustomerWithEx() {
		CustomerNotFoundException thrown=Assertions.assertThrows(CustomerNotFoundException.class, ()->customerService.updateCustomer(cust));
		assertTrue(thrown.getMessage().contains("Customer not found with ID: "));
	}
	
	@Test
	@DisplayName("get Customer By UserName And Password")
	public void getCustomerByUserNameAndPassword() {
		
	}
	
	@Test
	@DisplayName("get Customer By ID")
	public void getCustomerById() throws CustomerNotFoundException {
		when(customerRepository.findById(anyLong())).thenReturn(Optional.of(cust));
		Customer customer = customerService.getCustomerById(12l);
		assertNotNull(customer);
		assertEquals("Xyz", customer.getName());
		assertEquals("457963155", customer.getPhoneNumber());
	}
	@Test
	@DisplayName("get Customer By Customer With Exception")
	public void CustomerByIdWithEx() {
		CustomerNotFoundException thrown=Assertions.assertThrows(CustomerNotFoundException.class, ()->customerService.updateCustomer(cust));
		assertTrue(thrown.getMessage().contains("Customer not found with ID: "));
	}
}
