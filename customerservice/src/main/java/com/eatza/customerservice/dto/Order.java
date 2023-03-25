package com.eatza.customerservice.dto;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data

public class Order {
	
	private Long id;
	private Long customerId;
	private String status;
	private Long restaurantId;
	
    private LocalDateTime createDateTime;
    private LocalDateTime updateDateTime;
    
    
	public Order(Long customerId, String status, Long restaurantId) {
		this.customerId = customerId;
		this.status = status;
		this.restaurantId = restaurantId;
	}




	public Order() {
		super();
	}
	

}