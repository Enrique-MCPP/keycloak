package com.tutorial.bikeservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.tutorial.bikeservice.entity.Bike;
import com.tutorial.bikeservice.service.BikeService;

@RestController
@RequestMapping("/bike")
public class BikeController {

	@Autowired
	BikeService bikeService;

	private final WebClient webClient = WebClient.builder().build();

	@GetMapping
	public ResponseEntity<List<Bike>> getAll() {
		List<Bike> bikes = bikeService.getAll();
		if (bikes.isEmpty())
			return ResponseEntity.noContent().build();
		return ResponseEntity.ok(bikes);
	}

	@GetMapping("/calluser")
	@ResponseStatus(HttpStatus.OK)
	public String helloWebClient() {
		Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		String response = webClient.get().uri("http://localhost:8080/user").retrieve().bodyToMono(String.class).block();

		return "hello - message from microservice 2 -  " + response;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Bike> getById(@PathVariable("id") int id) {
		Bike bike = bikeService.getBikeById(id);
		if (bike == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(bike);
	}

	@PostMapping()
	public ResponseEntity<Bike> save(@RequestBody Bike bike) {
		Bike bikeNew = bikeService.save(bike);
		return ResponseEntity.ok(bikeNew);
	}

	@GetMapping("/byuser/{userId}")
	public ResponseEntity<List<Bike>> getByUserId(@PathVariable("userId") int userId) {
		List<Bike> bikes = bikeService.byUserId(userId);
		return ResponseEntity.ok(bikes);
	}

}
