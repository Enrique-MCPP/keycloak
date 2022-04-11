package com.tutorial.carservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.tutorial.carservice.entity.Car;
import com.tutorial.carservice.service.CarService;

@RestController
@RequestMapping("/car")
public class CarController {

	@Autowired
	CarService carService;
	private final WebClient webClient = WebClient.builder().build();

	@GetMapping
	public ResponseEntity<List<Car>> getAll() {
//		Keycloak instance = Keycloak.getInstance("http://keycloak:8080/auth", "microservices-realm", "admin", "admin",
//				"spring-cloud-gateway-client", "Ddq8OznjE8RsyR5x3IhcSJU8AGEwvsDm");
//		TokenManager tokenmanager = instance.tokenManager();
//		String accessToken = tokenmanager.getAccessTokenString();
//		Keycloak keycloak = KeycloakBuilder.builder() //
//				.serverUrl("http://localhost:8180/realms/microservices-realm/protocol/openid-connect/token") //
//				.realm("microservices-realm").grantType(OAuth2Constants.CLIENT_CREDENTIALS) //
//				.clientId("spring-cloud-gateway-client") //
//				.clientSecret("Ddq8OznjE8RsyR5x3IhcSJU8AGEwvsDm") //
//				.build();
//		AccessTokenResponse tok = keycloak.tokenManager().getAccessToken();
		Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		String response = webClient.get().uri("http://localhost:8080/user")
				.headers(header -> header.setBearerAuth(jwt.getTokenValue())).retrieve().bodyToMono(String.class)
				.block();
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
