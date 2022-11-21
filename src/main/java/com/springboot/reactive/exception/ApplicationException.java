package com.springboot.reactive.exception;

public class ApplicationException extends RuntimeException {

	private static final long serialVersionUID = -6787509122998953183L;

	public ApplicationException(String errorMessage) {
		super(errorMessage);
	}
}
