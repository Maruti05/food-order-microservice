package com.eatza.reviewservice.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.eatza.reviewservice.dto.RestaurantDto;


@FeignClient(name = "API-GATEWAY")
public interface RestaurantClient {
	@GetMapping("/restaurant/api/v1/get-restaurant/id/{id}")
	public RestaurantDto getRestaurantsById(@PathVariable Long id);
}
