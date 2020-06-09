package com.phone.api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Document
@Builder
@Getter
@Setter
public class Provider {

	@Id
	private String id;
	private String name;
	private String prefix;
	private int cost;

}
