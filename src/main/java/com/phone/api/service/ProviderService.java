package com.phone.api.service;

import java.util.List;

import com.phone.api.model.Provider;

public interface ProviderService {

	public List<Provider> getProvidersByPrefix(String prefix);
	
}
