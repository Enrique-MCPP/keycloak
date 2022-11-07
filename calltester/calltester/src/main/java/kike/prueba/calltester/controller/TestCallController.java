package kike.prueba.calltester.controller;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("/testcall")
public class TestCallController {

	private final WebClient webClient = WebClient.builder().build();


	@GetMapping("/token")
	public void getToken() throws IOException, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("http://localhost:8180/realms/microservices-realm/protocol/openid-connect/token"))
				.headers("Accept", "application/json", "Content-Type", "application/x-www-form-urlencoded")
				.POST(BodyPublishers.ofString("grant_type=client_credentials&client_id=spring-cloud-gateway-client"
						+ "&client_secret=8muCUimd0woQCBkab3UMxQUFcWcbZc2S"))
				.build();
		HttpResponse<?> response = client.send(request, BodyHandlers.ofString());
		System.out.println(response.statusCode() + response.body().toString());
		JSONObject objJsonObject = new JSONObject(response.body().toString());
		String token = objJsonObject.getString("access_token");
		String responseUser = webClient.get().uri("http://localhost:8080/bike/calluser")
				.headers(header -> header.setBearerAuth(token)).retrieve().bodyToMono(String.class).block();
		System.out.println(responseUser);
	}

}
