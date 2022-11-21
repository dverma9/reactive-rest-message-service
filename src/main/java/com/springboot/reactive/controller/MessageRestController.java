package com.springboot.reactive.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.reactive.exception.ApplicationException;
import com.springboot.reactive.model.Message;
import com.springboot.reactive.model.MessageRequest;
import com.springboot.reactive.service.MessageService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1")
public class MessageRestController {

	@Autowired 
	private MessageService messageService;


	@PostMapping(value = "/message", consumes = MediaType.APPLICATION_STREAM_JSON_VALUE, 
			  produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
	public Mono<String> postMessage(@Valid @RequestBody MessageRequest messageRequest) {
		messageService.postMessage(messageRequest);
		return Mono.just("Message Posted Successfully");
	}

	@GetMapping(value = "/message/{id}", consumes = MediaType.APPLICATION_STREAM_JSON_VALUE, 
			  produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
	public Mono<Message> getMessage(@PathVariable Integer id) {
		return messageService.getMessage(id)
				.switchIfEmpty(Mono.error(new ApplicationException("Message does not exist with id: "+ id)));
	}

	@GetMapping(value = "/messages/{pageNo}/{pageSize}")
	public Flux<Message> getAllMessages(@PathVariable Integer pageNo, @PathVariable Integer pageSize,
			@RequestParam(required = false) String userId, @RequestParam(required = false) String topic) {
		return messageService.getPaginatedMessages(pageNo, pageSize, userId, topic)
				.switchIfEmpty(Flux.error(new ApplicationException("No Message available for selected filters values")));
	}

	@DeleteMapping("/message/{id}")
	public Mono<Message> deleteMessage(@PathVariable Integer id) {
		return messageService.deleteMessage(id)
				.switchIfEmpty(Mono.error(new ApplicationException("User is not allowed to delete Message with id: "+ id)));
	}

}
