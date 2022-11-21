package com.springboot.reactive.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.springboot.reactive.model.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
	
		return new ResponseEntity<>(ErrorResponse.builder()
				.code(HttpStatus.BAD_REQUEST.value())
				.message("Validation Failed: " +ex.getFieldError().toString())
				.time(LocalDateTime.now())
				.build(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
	
		return new ResponseEntity<>(ErrorResponse.builder()
				.code(HttpStatus.BAD_REQUEST.value())
				.message(ex.getMessage())
				.time(LocalDateTime.now())
				.build(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ApplicationException.class)
	public ResponseEntity<ErrorResponse> handleApplicationException(ApplicationException ex) {
	
		return new ResponseEntity<>(ErrorResponse.builder()
				.code(HttpStatus.NOT_FOUND.value())
				.message(ex.getMessage())
				.time(LocalDateTime.now())
				.build(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleAllExceptions(Exception ex) {
		
		return new ResponseEntity<>(ErrorResponse.builder()
				.code(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.message("Internal Server Error")
				.time(LocalDateTime.now())
				.build(), HttpStatus.INTERNAL_SERVER_ERROR);

	}

}
