package com.eatza.reviewservice.service.reviewservice;

import java.util.List;

import com.eatza.reviewservice.dto.CustomerReviewDto;
import com.eatza.reviewservice.dto.ReviewDto;
import com.eatza.reviewservice.exception.CustomerNotFoundException;
import com.eatza.reviewservice.exception.ReviewNotFoundException;
import com.eatza.reviewservice.model.Review;

public interface ReviewService {

	Review addReview(Review review);

	ReviewDto getReviewOfRestaurant(Long restaurantId);

	CustomerReviewDto getReviewOfRestaurantByCustomer(Long customerId) throws CustomerNotFoundException;

	Review updateReview(Review review) throws ReviewNotFoundException;

}
