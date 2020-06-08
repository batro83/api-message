package com.phone.api.service.impl;

import static org.slf4j.LoggerFactory.getLogger;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import com.phone.api.dto.ResponseDto;
import com.phone.api.model.Provider;
import com.phone.api.service.ProviderService;
import com.phone.api.service.SendService;

@Service
public class SendServiceImpl implements SendService {

	private static final Logger LOG = getLogger(SendServiceImpl.class);

	private ProviderService providerService;

	private SendServiceImpl(ProviderService providerService) {
		this.providerService = providerService;
	}

	@Override
	public List<ResponseDto> send(String mobile, String message) {
		List<ResponseDto> response = new ArrayList<>();
		List<Provider> providerList = providerService.getProvidersByPrefix(mobile.substring(0, 4));

		for (Provider provider : providerList) {
			ResponseDto dto = ResponseDto.builder()
				.operationId(UUID.randomUUID().toString())
				.providerName(provider.getName())
				.build();
			// Maybe we can insert operation in a db and use UUID from his _id
			response.add(dto);
		}
		LOG.info("Message sended to {} ", mobile);
		return response;
	}

}
