package ca.team3.laps.configuration;

import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

	@Bean
    public WebClient webClient(WebClient.Builder webClientBuilder) {
		return webClientBuilder
				.baseUrl("https://calendarific.com/api/v2/holidays")
				.build();
	}

}