package com.eatza.reviewservice.dto;

import java.util.List;

import com.eatza.reviewservice.model.Review;

import lombok.Data;

@Data
public class ReviewDto {

	private RestaurantDto restaurant;
	
	private List<Review> reviews;
	

	public ReviewDto(RestaurantDto restaurant, List<Review> reviews) {
		super();
		this.restaurant = restaurant;
		this.reviews = reviews;
	}

	public ReviewDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
}
