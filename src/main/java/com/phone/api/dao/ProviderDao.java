package com.phone.api.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.phone.api.model.Provider;

public interface ProviderDao extends PagingAndSortingRepository<Provider, String> {
	
	public List<Provider> getProviderByPrefix(String prefix);
	
}




