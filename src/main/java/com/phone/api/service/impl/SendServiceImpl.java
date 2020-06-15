package com.phone.api.service.impl;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;
import static org.slf4j.LoggerFactory.getLogger;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import com.phone.api.dto.ResponseDto;
import com.phone.api.model.Provider;
import com.phone.api.provider.IProvider;
import com.phone.api.service.ProviderService;
import com.phone.api.service.SendService;

@Service
public class SendServiceImpl implements SendService {

	private static final Logger LOG = getLogger(SendServiceImpl.class);
	private ProviderService providerService;
	@Resource
	private Map<String, IProvider> mapProvider;

	private SendServiceImpl(ProviderService providerService) {
		this.providerService = providerService;
	}

	@Override
	public List<ResponseDto> send(String mobile, String message) {
		List<Provider> providerList = providerService.getProvidersByPrefix(mobile.substring(0, 4));
		Provider selectedProvider = providerList.stream().findFirst().orElse(null);
		ofNullable(selectedProvider).ifPresent(x -> mapProvider.get(x.getName()).send(mobile, message));

		List<ResponseDto> response = ofNullable(providerList).orElseGet(Collections::emptyList)
			.stream()
			.map(provider -> {
				ResponseDto dto = ResponseDto.builder()
					.operationId(UUID.randomUUID().toString())
					.providerName(provider.getName())
					.build();
				// Maybe we can insert operation in a db and use UUID from his _id
				return dto;
			})
			.collect(toList());

		LOG.info("Message sended to {} ", mobile);
		return response;
	}
	
}
