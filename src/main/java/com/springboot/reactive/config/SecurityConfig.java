package com.springboot.reactive.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf()
		.disable()
		.authorizeRequests()
		.antMatchers("/api/v1/message*/*")
		.authenticated()
		.and()
		.httpBasic()
		.and()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		return http.build();
	}

	@Bean
	public InMemoryUserDetailsManager userDetailsService() {
		UserDetails user1 = User.withUsername("dverma").password("{noop}password").roles("USER")
				.build();
		UserDetails user2 = User.withUsername("rsharma").password("{noop}password").roles("USER")
				.build();
		UserDetails user3 = User.withUsername("mali").password("{noop}password").roles("USER")
				.build();
		UserDetails user4 = User.withUsername("mparupalli").password("{noop}password").roles("USER")
				.build();
		UserDetails user5 = User.withUsername("usaxena").password("{noop}password").roles("USER")
				.build();
		return new InMemoryUserDetailsManager(user1, user2, user3, user4, user5);
	}

}
