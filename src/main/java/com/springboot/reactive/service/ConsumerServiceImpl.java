package com.springboot.reactive.service;

import java.util.Arrays;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import org.springframework.stereotype.Service;

import com.springboot.reactive.model.Message;
import com.springboot.reactive.repository.ReactiveMessageRepository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Service
@Slf4j
public class ConsumerServiceImpl implements CommandLineRunner {
	private final ReactiveKafkaConsumerTemplate<String, Message> reactiveKafkaConsumerTemplate;
	
	private ReactiveMessageRepository reactiveMessageRepository;

    public ConsumerServiceImpl(ReactiveKafkaConsumerTemplate<String, Message> reactiveKafkaConsumerTemplate, 
    		ReactiveMessageRepository reactiveMessageRepository) {
        this.reactiveKafkaConsumerTemplate = reactiveKafkaConsumerTemplate;
        this.reactiveMessageRepository = reactiveMessageRepository;
    }
    
    @Value("${message.forbidden.words:password,secret}")
    private String forbiddenWords;

    private Flux<Message> consumeMessage() {
        return reactiveKafkaConsumerTemplate
                .receiveAutoAck()
                .doOnNext(consumerRecord -> log.info("received key={}, value={} from topic={}, offset={}",
                        consumerRecord.key(),
                        consumerRecord.value(),
                        consumerRecord.topic(),
                        consumerRecord.offset())
                )
                .map(ConsumerRecord::value)
                .doOnNext(message -> log.info("Successfully consumed {} = {}", Message.class.getSimpleName(), message))
                .filter(message -> !Arrays.stream(forbiddenWords.split(",")).anyMatch(message.getContent()::contains))
                .flatMap(reactiveMessageRepository::save)
                .doOnError(throwable -> log.error("Error occurred while consuming message : {}", throwable.getMessage()));
    }

    @Override
    public void run(String... args) {
    	consumeMessage().subscribe();
    }
}
