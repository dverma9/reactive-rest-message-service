package com.springboot.reactive.config;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;

import com.springboot.reactive.model.Message;

import reactor.kafka.receiver.ReceiverOptions;

@Configuration
public class KafkaConsumerConfig {

	@Bean
	public ReceiverOptions<String, Message> kafkaReceiverOptions(@Value(value = "${kafka.topic.name}") String topic,
			KafkaProperties kafkaProperties) {
		ReceiverOptions<String, Message> basicReceiverOptions = ReceiverOptions
				.create(kafkaProperties.buildConsumerProperties());
		return basicReceiverOptions.subscription(Collections.singletonList(topic));
	}

	@Bean
	public ReactiveKafkaConsumerTemplate<String, Message> reactiveKafkaConsumerTemplate(
			ReceiverOptions<String, Message> kafkaReceiverOptions) {
		return new ReactiveKafkaConsumerTemplate<String, Message>(kafkaReceiverOptions);
	}

}
