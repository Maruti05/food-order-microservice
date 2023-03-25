package com.eatza.reviewservice.dto;

import java.util.List;

import com.eatza.reviewservice.model.Review;

import lombok.Data;

@Data
public class CustomerReviewDto {
private List<Review> reviews;
	
	private CustomerDto customer;

	public CustomerReviewDto(List<Review> reviews, CustomerDto customer) {
		super();
		this.reviews = reviews;
		this.customer = customer;
	}

	public CustomerReviewDto() {
		// TODO Auto-generated constructor stub
	}
	
	
}
