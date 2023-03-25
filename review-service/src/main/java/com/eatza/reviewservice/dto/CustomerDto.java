package com.eatza.reviewservice.dto;



import lombok.Data;

@Data
public class CustomerDto {

	private Long id;
	
	private String name;
	
	private String email;
	
	private String password;
	
	private String phoneNumber;

	public CustomerDto(String name, String email, String phoneNumber) {
		super();
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

	public CustomerDto(String email, String phoneNumber) {
		super();
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

	public CustomerDto() {
		super();
	}
	
	
	

}
