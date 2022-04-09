package com.tutorial.carservice.controller;

import java.util.List;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tutorial.carservice.entity.Car;
import com.tutorial.carservice.service.CarService;

@RestController
@RequestMapping("/car")
public class CarController {

	@Autowired
	CarService carService;

	@GetMapping
	public ResponseEntity<List<Car>> getAll() {
		Keycloak keycloak = KeycloakBuilder.builder() //
				.serverUrl("http://localhost:8180/realms/microservices-realm/protocol/openid-connect/token") //
				.grantType(OAuth2Constants.CLIENT_CREDENTIALS) //
				.clientId("spring-cloud-gateway-client") //
				.clientSecret("Ddq8OznjE8RsyR5x3IhcSJU8AGEwvsDm") //
				.build();
		AccessTokenResponse tok = keycloak.tokenManager().getAccessToken();
		List<Car> cars = carService.getAll();
		if (cars.isEmpty())
			return ResponseEntity.noContent().build();
		return ResponseEntity.ok(cars);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Car> getById(@PathVariable("id") int id) {
		Car car = carService.getCarById(id);
		if (car == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(car);
	}

	@PostMapping()
	public ResponseEntity<Car> save(@RequestBody Car car) {
		Car carNew = carService.save(car);
		return ResponseEntity.ok(carNew);
	}

	@GetMapping("/byuser/{userId}")
	public ResponseEntity<List<Car>> getByUserId(@PathVariable("userId") int userId) {
		List<Car> cars = carService.byUserId(userId);
		return ResponseEntity.ok(cars);
	}

}
