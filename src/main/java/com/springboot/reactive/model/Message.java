package com.springboot.reactive.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message implements Serializable {

	private static final long serialVersionUID = -3479936149565999750L;
	
	@Id
	private Integer id;
	private String userId;
	private String topic;
	private String content;
	private LocalDateTime created;
}
