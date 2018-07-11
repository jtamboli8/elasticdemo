package org.jatin.elasticdemo.configuration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.Configuration.ConfigurationBuilder;
import com.jayway.jsonpath.spi.json.JacksonJsonNodeJsonProvider;

@org.springframework.context.annotation.Configuration
public class ElasticDemoConfigurationFile {
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
		return restTemplateBuilder.build();
	}
	
	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}
	
	@Bean
	Configuration getConfigurationForJsonPath() {
		ConfigurationBuilder configurationBuilder = Configuration.builder()
				.jsonProvider(new JacksonJsonNodeJsonProvider());
		Configuration configuration = configurationBuilder.build();
		return configuration;
	}

}
