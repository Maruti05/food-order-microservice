package com.eatza.order.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.eatza.order.dto.ItemFetchDto;
import com.eatza.order.dto.RestaurantFetchDto;


@FeignClient(name = "API-GATEWAY")
public interface RestaurantClient {
	@GetMapping("/restaurant/api/v1/get-restaurant/id/{id}")
	public RestaurantFetchDto getRestaurantsById(@PathVariable Long id);
	
	@GetMapping("/menu-item/api/v1/item/id/{id}")
	public ItemFetchDto getItemById( @PathVariable Long id);
}
