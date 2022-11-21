package com.springboot.reactive.model;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {

	private int code;
	private String message;
	private LocalDateTime time;
	
}
