package com.springboot.reactive.service;

import com.springboot.reactive.model.Message;
import com.springboot.reactive.model.MessageRequest;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MessageService {

	void postMessage(MessageRequest messageRequest);
	
	Mono<Message> getMessage(Integer id);
	
	Flux<Message> getPaginatedMessages(Integer pageNo, Integer pageSize, String userId, String topic);
	
	Mono<Message> deleteMessage(Integer id);
}
