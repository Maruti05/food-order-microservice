package com.eatza.reviewservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eatza.reviewservice.model.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
 Optional<List<Review>> findByRestaurantId(Long restaurantId);
 Optional< List<Review>> findByCustmerId(Long custmerId);
}
