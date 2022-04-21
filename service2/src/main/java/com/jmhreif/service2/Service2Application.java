package com.jmhreif.service2;

import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class Service2Application {

	public static void main(String[] args) {
		SpringApplication.run(Service2Application.class, args);
	}

	@Bean
	WebClient client() {
		return WebClient.create("http://localhost:8081");
	}

}

@RestController
@AllArgsConstructor
@RequestMapping("/hello")
class TextController {
	private final WebClient client;

	@GetMapping
	Mono<String> getText() {
		return client.get()
				.uri("/text")
				.retrieve()
				.bodyToMono(String.class);
	}
}
