package com.example.Bot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class BotApplication {

	public static void main(String[] args) {
		SpringApplication.run(BotApplication.class, args);
	}

//	@Bean
//
//	public WebClient webClientUser(WebClient.Builder builder) {
//		return builder
//				.baseUrl("http://localhost:8081")
//				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//				.build();
//	}
	@Bean
	public WebClient webClienStock(WebClient.Builder builder) {
		return builder
				.baseUrl("http://localhost:8082")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.build();
	}


}
