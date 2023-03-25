package com.eatza.reviewservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eatza.reviewservice.dto.CustomerReviewDto;
import com.eatza.reviewservice.dto.ReviewDto;
import com.eatza.reviewservice.exception.CustomerNotFoundException;
import com.eatza.reviewservice.exception.ReviewNotFoundException;
import com.eatza.reviewservice.model.Review;
import com.eatza.reviewservice.service.reviewservice.ReviewService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
@RefreshScope
@RestController
@RequestMapping(value = "/review/api/v1")
public class ReviewController {
	private static final Logger logger = LoggerFactory.getLogger(ReviewController.class);
	@Autowired
	private ReviewService reviewService;
	
	@PostMapping(value = "/add-review")
	public ResponseEntity<?> addReview(@RequestBody Review review){
		logger.debug("In add review method, calling service");
		Review review2= reviewService.addReview(review);
		return ResponseEntity.ok(review2);
	}
	@HystrixCommand(fallbackMethod = "getReviewOfRestaurantByCustomerFallback")
	@GetMapping(value = "/get-review-of-restaurant",params ="restaurantId")
	public  ResponseEntity<?> getReviewOfRestaurant(@RequestParam Long restaurantId){
		logger.debug("In get review of restaurant method, calling service");
		ReviewDto reviews= reviewService.getReviewOfRestaurant(restaurantId);
		return ResponseEntity.ok(reviews);
		
	}
	@HystrixCommand(fallbackMethod = "getReviewOfRestaurantByCustomerFallback")
	@GetMapping(value = "/get-review-of-restaurant-by-customer",params ="customerId")
	public  ResponseEntity<?> getReviewOfRestaurantByCustomer(@RequestParam Long customerId) throws CustomerNotFoundException{
		logger.debug("In get Review Of Restaurant By Customer method, calling service");
		CustomerReviewDto reviews= reviewService.getReviewOfRestaurantByCustomer(customerId);
		return ResponseEntity.ok(reviews);
		
	}
	
	@PutMapping(value = "/update-review")
	public ResponseEntity<?> updateReview(@RequestBody Review review) throws ReviewNotFoundException{
		logger.debug("In update Review Of Restaurant, calling service");
		Review updateReview= reviewService.updateReview(review);
		return ResponseEntity.ok(updateReview);
	}
	
	public ResponseEntity<?> getReviewOfRestaurantByCustomerFallback(Long customerId){
		logger.debug("In get Review Of Restaurant By Customer Fallback");
		return ResponseEntity.internalServerError().body("Server not able reach !!");
	}
}
