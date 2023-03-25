package com.eatza.apigateway.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallBackController {

	@GetMapping(value = "message")
	public ResponseEntity<?> fallBack() {
		return ResponseEntity.ok("Somthing went wrong");
		
	}
}
