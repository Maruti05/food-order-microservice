package com.eatza.reviewservice.dto;

import lombok.Data;

@Data
public class RestaurantDto {
	

	private Long id;
	private String name;
	private String location;
	private String cuisine;
	private int budget;
	private double rating;

	public RestaurantDto(String name, String location, String cuisine, int budget, double rating) {
		super();
		this.name = name;
		this.location = location;
		this.cuisine = cuisine;
		this.budget = budget;
		this.rating = rating;
	}
	
}
