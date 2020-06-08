package com.phone.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ResponseDto {
	
	private String operationId;
	private String providerName;

}
