package com.eatza.reviewservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name= "reviews")
public class Review {
 
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String feedback;
	
	private Double rating;
	
	private Long custmerId;
	
	private Long restaurantId;
	
	

	public Review() {
		super();
	}



	public Review(String feedback, Double rating, Long custmerId, Long restaurantId) {
		super();
		this.feedback = feedback;
		this.rating = rating;
		this.custmerId = custmerId;
		this.restaurantId = restaurantId;
	}
	
	
}
