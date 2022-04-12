package kike.prueba.calltester.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("/testcall")
public class TestCallController {

	private final WebClient webClient = WebClient.builder().build();

	@GetMapping
	public String getAll() throws IOException, InterruptedException {
//		Keycloak instance = Keycloak.getInstance("http://keycloak:8080/auth", "microservices-realm", "admin", "admin",
//				"spring-cloud-gateway-client", "Ddq8OznjE8RsyR5x3IhcSJU8AGEwvsDm");
//
//		TokenManager tokenmanager = instance.tokenManager();
//		String accessToken = tokenmanager.getAccessTokenString();
//		String response = webClient.get()
//				.uri("http://localhost:8180/realms/microservices-realm/protocol/openid-connect/token")
//				.headers(header -> header.setBearerAuth(accessToken)).retrieve().bodyToMono(String.class).block();
//		Keycloak keycloak = KeycloakBuilder.builder() //
//				.serverUrl("http://localhost:8180/auth") //
//				.realm("microservices-realm").grantType(OAuth2Constants.CLIENT_CREDENTIALS) //
//				.clientId("spring-cloud-gateway-client") //
//				.clientSecret("Ddq8OznjE8RsyR5x3IhcSJU8AGEwvsDm")
//				.build();
//		
//		AccessTokenResponse tok = keycloak.tokenManager().getAccessToken();
		// Cliend id and client secret
		var keys = "spring-cloud-gateway-client:Ddq8OznjE8RsyR5x3IhcSJU8AGEwvsDm";
		var URL = "http://localhost:8180/realms/microservices-realm/protocol/openid-connect/token";

		HashMap<String, String> parameters = new HashMap<>();
		parameters.put("grant_type", "client_credentials");
		String form = parameters.keySet().stream()
				.map(key -> key + "=" + URLEncoder.encode(parameters.get(key), StandardCharsets.UTF_8))
				.collect(Collectors.joining("&"));

		String encoding = Base64.getEncoder().encodeToString(keys.getBytes());
		HttpClient client = HttpClient.newHttpClient();

		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(URL))
				.headers("Content-Type", "application/x-www-form-urlencoded", "Authorization", "Basic " + encoding)
				.POST(BodyPublishers.ofString(form)).build();
		HttpResponse<?> response = client.send(request, BodyHandlers.ofString());
		System.out.println(response.statusCode() + response.body().toString());

		return "";
	}

	@GetMapping("/token")
	public void getToken() throws IOException, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("http://localhost:8180/realms/microservices-realm/protocol/openid-connect/token"))
				.headers("Accept", "application/json", "Content-Type", "application/x-www-form-urlencoded")
				.POST(BodyPublishers.ofString("grant_type=client_credentials&client_id=spring-cloud-gateway-client"
						+ "&client_secret=Ddq8OznjE8RsyR5x3IhcSJU8AGEwvsDm"))
				.build();
		HttpResponse<?> response = client.send(request, BodyHandlers.ofString());
		System.out.println(response.statusCode() + response.body().toString());
		JSONObject objJsonObject = new JSONObject(response.body().toString());
		String token = objJsonObject.getString("access_token");
		String responseUser = webClient.get().uri("http://localhost:8080/user")
				.headers(header -> header.setBearerAuth(token)).retrieve().bodyToMono(String.class).block();
		System.out.println(responseUser);
	}

}
