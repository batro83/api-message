package com.phone.api.service;

import java.util.List;

import com.phone.api.dto.ResponseDto;

public interface SendService {
	
	public List<ResponseDto> send(String mobile, String message);
}
