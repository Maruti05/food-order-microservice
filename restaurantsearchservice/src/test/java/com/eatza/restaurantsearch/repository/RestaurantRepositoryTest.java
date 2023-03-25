package com.eatza.restaurantsearch.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import com.eatza.restaurantsearch.model.Restaurant;

@DataJpaTest
public class RestaurantRepositoryTest {
	
	
	@Autowired
	RestaurantRepository restaurantRepository;
	
	
	@Test
	public void findAll() {
		Pageable pageable = PageRequest.of(1, 10);
		Page<Restaurant> restaurants=restaurantRepository.findAll(pageable);
		assertEquals(3, restaurants.getTotalElements());
	}
	
	@Test
	public void findByNameContaining() {
		Pageable pageable = PageRequest.of(1, 10);
		Page<Restaurant> restaurants=restaurantRepository.findByNameContaining("Dominos",pageable);
		assertEquals(1, restaurants.getTotalElements());
	}
	
	@Test
	public void findByRatingGreaterThanEqual() {
		Pageable pageable = PageRequest.of(1, 10);
		Page<Restaurant> restaurants=restaurantRepository.findByRatingGreaterThanEqual(4.3,pageable);
		assertEquals(2, restaurants.getTotalElements());
	}
	
	@Test
	public void findByLocationContainingAndCuisineContaining() {
		Pageable pageable = PageRequest.of(1, 10);
		Page<Restaurant> restaurants=restaurantRepository.findByLocationContainingAndCuisineContaining("rr","Soprole",pageable);
		assertEquals(1, restaurants.getTotalElements());
	}
	@Test
	public void findByLocationContainingAndNameContaining() {
		Pageable pageable = PageRequest.of(1, 10);
		Page<Restaurant> restaurants=restaurantRepository.findByLocationContainingAndNameContaining("rr","dominos",pageable);
		assertEquals(0, restaurants.getTotalElements());
	}


}
