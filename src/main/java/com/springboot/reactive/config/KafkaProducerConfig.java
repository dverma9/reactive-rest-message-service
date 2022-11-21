package com.springboot.reactive.config;

import java.util.Map;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;

import com.springboot.reactive.model.Message;

import reactor.kafka.sender.SenderOptions;

@Configuration
public class KafkaProducerConfig {
	
	@Bean
	public ReactiveKafkaProducerTemplate<String, Message> reactiveKafkaProducerTemplate(KafkaProperties properties) {
		Map<String, Object> props = properties.buildProducerProperties();
		return new ReactiveKafkaProducerTemplate<String, Message>(SenderOptions.create(props));
	}
}
