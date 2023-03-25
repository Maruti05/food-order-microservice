package com.eatza.reviewservice.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.eatza.reviewservice.dto.CustomerDto;

@FeignClient(name = "API-GATEWAY")
public interface CustomerClient {
	@GetMapping(value = "/customer/api/v1/get-customer",params = {"username","password"})
	public CustomerDto getCustomer(@RequestParam String username,@RequestParam String password);
	
	@GetMapping(value = "/customer/api/v1/get-customer-by-id",params = {"id"})
	public CustomerDto getCustomerById(@RequestParam Long id);
}
