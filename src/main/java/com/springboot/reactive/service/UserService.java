package com.springboot.reactive.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserService {

	public String getUserId() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		log.debug(auth.getName());
		return auth.getName();
	}
}
