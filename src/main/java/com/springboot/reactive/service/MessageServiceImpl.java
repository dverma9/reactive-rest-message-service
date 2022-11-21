package com.springboot.reactive.service;

import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.reactive.model.Message;
import com.springboot.reactive.model.MessageRequest;
import com.springboot.reactive.repository.ReactiveMessageRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private ReactiveMessageRepository reactiveMessageRepository;

	@Autowired
	private ProducerService producerService;

	@Autowired
	private UserService userService;

	@Override
	public void postMessage(MessageRequest messageRequest) {
		Message message = Message.builder()
				.userId(userService.getUserId())
				.topic(messageRequest.getTopic())
				.content(messageRequest.getContent())
				.created(LocalDateTime.now())
				.build();
		// publishing message to kafka topic
		producerService.send(message);
	}

	@Override
	public Flux<Message> getPaginatedMessages(Integer pageNo, Integer pageSize, String userId, String topic) {
		Pageable paging = PageRequest.of(pageNo, pageSize);
		if (userId == null && topic == null) {
			return reactiveMessageRepository.findAllByIdNotNullOrderByIdAsc(paging);
		} else if (userId == null && topic != null) {
			return reactiveMessageRepository.findAllByIdNotNullAndTopicOrderByIdAsc(topic, paging);
		} else if (userId != null && topic == null) {
			return reactiveMessageRepository.findAllByIdNotNullAndUserIdOrderByIdAsc(userId, paging);
		} else {
			return reactiveMessageRepository.findAllByIdNotNullAndTopicAndUserIdOrderByIdAsc(topic, userId, paging);
		}
	}

	@Override
	public Mono<Message> getMessage(Integer id) {
		return reactiveMessageRepository.findById(id);
	}

	@Override
	public Mono<Message> deleteMessage(Integer id) {

		return reactiveMessageRepository.findByIdAndUserId(id, userService.getUserId())
		.flatMap(messageToBeDeleted -> reactiveMessageRepository.delete(messageToBeDeleted)
				.then(Mono.just(messageToBeDeleted)));
	}

}
