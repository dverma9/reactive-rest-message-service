package com.springboot.reactive.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;

import com.springboot.reactive.model.Message;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ReactiveMessageRepository extends ReactiveSortingRepository<Message, Integer> {

	Mono<Message> deleteByIdAndUserId(Integer id, String userId);
	
	Flux<Message> findAllByIdNotNullAndTopicAndUserIdOrderByIdAsc(String topic, String userId, Pageable paging);
	
	Flux<Message> findAllByIdNotNullAndUserIdOrderByIdAsc(String userId, Pageable paging);
	
	Flux<Message> findAllByIdNotNullAndTopicOrderByIdAsc(String topic, Pageable paging);
	
	Flux<Message> findAllByIdNotNullOrderByIdAsc(final Pageable paging);

	Mono<Message> findByIdAndUserId(Integer id, String userId);
	
	
}
