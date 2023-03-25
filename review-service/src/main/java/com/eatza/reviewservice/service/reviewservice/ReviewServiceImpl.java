package com.eatza.reviewservice.service.reviewservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eatza.reviewservice.dto.CustomerDto;
import com.eatza.reviewservice.dto.CustomerReviewDto;
import com.eatza.reviewservice.dto.RestaurantDto;
import com.eatza.reviewservice.dto.ReviewDto;
import com.eatza.reviewservice.exception.CustomerNotFoundException;
import com.eatza.reviewservice.exception.RestaurantNotFoundException;
import com.eatza.reviewservice.exception.ReviewNotFoundException;
import com.eatza.reviewservice.exception.ServerNotReachable;
import com.eatza.reviewservice.feignclient.CustomerClient;
import com.eatza.reviewservice.feignclient.RestaurantClient;
import com.eatza.reviewservice.model.Review;
import com.eatza.reviewservice.repository.ReviewRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private ReviewRepository repository;

	@Autowired
	private RestaurantClient restaurantClient;

	@Autowired
	private CustomerClient CustomerClient;

	@Override
	public Review addReview(Review review) {
		Review savedReview = repository.save(review);
		return savedReview;
	}

	
	@Override
	public ReviewDto getReviewOfRestaurant(Long restaurantId) {
		RestaurantDto restaurant = restaurantClient.getRestaurantsById(restaurantId);
		List<Review> listOfReview = repository.findByRestaurantId(restaurantId).get();
		return new ReviewDto(restaurant, listOfReview);
	}

	@Override
	public CustomerReviewDto getReviewOfRestaurantByCustomer(Long customerId) throws CustomerNotFoundException {
		CustomerDto customer = CustomerClient.getCustomerById(customerId);
		List<Review> listOfReview = repository.findByCustmerId(customerId).get();
		return new CustomerReviewDto(listOfReview, customer);
	}

	@Override
	public Review updateReview(Review review) throws ReviewNotFoundException {
		Review rev = repository.findById(review.getId())
				.orElseThrow(() -> new ReviewNotFoundException("Review Not found with id : " + review.getId()));
		rev.setFeedback(review.getFeedback());
		rev.setRating(review.getRating());
		Review savedReview = repository.save(rev);
		return savedReview;
	}

	
}
