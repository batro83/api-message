package com.phone.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phone.api.dto.MessageDto;
import com.phone.api.dto.ResponseDto;
import com.phone.api.service.SendService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/message")
public class MessageController {

	private SendService sendService;
	
	private MessageController(SendService sendService) {
		this.sendService = sendService;
	}

	@ApiOperation(value = "Send message")
	@PostMapping("/{mobileNumber}")
	public ResponseEntity<List<ResponseDto>> findMovie(@PathVariable("mobileNumber") String mobile,
			@RequestBody MessageDto dto) throws Exception {
		return ResponseEntity.ok(sendService.send(mobile, dto.getBody()));
	}
}