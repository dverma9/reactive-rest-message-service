package com.springboot.reactive.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import org.springframework.stereotype.Service;

import com.springboot.reactive.model.Message;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProducerService {

    private final ReactiveKafkaProducerTemplate<String, Message> reactiveKafkaProducerTemplate;

    @Value(value = "${kafka.topic.name}")
    private String topic;

    public ProducerService(ReactiveKafkaProducerTemplate<String, Message> reactiveKafkaProducerTemplate) {
        this.reactiveKafkaProducerTemplate = reactiveKafkaProducerTemplate;
    }

    public void send(Message Message) {
        log.info("send to topic={}, {}={},", topic, Message.class.getSimpleName(), Message);
        reactiveKafkaProducerTemplate.send(topic, Message)
                .doOnSuccess(senderResult -> log.info("sent {} offset : {}", Message, senderResult.recordMetadata().offset()))
                .subscribe();

    }
}
