package com.springboot.reactive.model;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageRequest implements Serializable {

	private static final long serialVersionUID = 8884303552022806708L;
	
	@NotBlank
	private String topic;
	private String content;

}
