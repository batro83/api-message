package com.phone.api.service.impl;

import static java.util.Collections.emptyList;

import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.phone.api.dao.ProviderDao;
import com.phone.api.model.Provider;
import com.phone.api.service.ProviderService;

@Service
public class ProviderServiceImpl implements ProviderService {

	private ProviderDao providerDao;

	private ProviderServiceImpl(ProviderDao providerDao) {
		this.providerDao = providerDao;
	}

	@Override
	public List<Provider> getProvidersByPrefix(String prefix) {
		List<Provider> groupedHoted = providerDao.getProviderByPrefix(prefix);
		return getLowCostProvider(groupedHoted);
	}

	private List<Provider> getLowCostProvider(List<Provider> groupedHoted2) {
		List<Provider> list = emptyList();
		NavigableMap<Integer, List<Provider>> groupedProvidersByCost = groupedHoted2.stream()
			.collect(Collectors.groupingBy(Provider::getCost, TreeMap::new, Collectors.toList()));

		if (!groupedProvidersByCost.isEmpty())
			list = groupedProvidersByCost.firstEntry().getValue();
		return list;
	}

}
