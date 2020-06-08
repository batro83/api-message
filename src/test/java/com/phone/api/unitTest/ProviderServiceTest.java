package com.phone.api.unitTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.phone.api.dao.ProviderDao;
import com.phone.api.model.Provider;
import com.phone.api.service.impl.ProviderServiceImpl;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProviderServiceTest {

	@Mock
	private ProviderDao providerDao;

	@InjectMocks
	private ProviderServiceImpl providerService;

	@Test
	public void test001_shouldGetLowCostProviders() {
		List<Provider> providerList = generateProviderList();

		when(providerDao.getProviderByPrefix(("0034"))).thenReturn(providerList);

		List<Provider> lowPriceProviders = providerService.getProvidersByPrefix("0034");

		assertEquals(2, lowPriceProviders.size());
		assertEquals(lowPriceProviders.get(0).getName(), "P1");
		assertEquals(lowPriceProviders.get(0).getPrefix(), "0034");
		assertEquals(lowPriceProviders.get(0).getCost(), 1);
		assertEquals(lowPriceProviders.get(1).getName(), "P3");
		assertEquals(lowPriceProviders.get(1).getPrefix(), "0034");
		assertEquals(lowPriceProviders.get(1).getCost(), 1);
	}

	private List<Provider> generateProviderList() {
		List<Provider> list = new ArrayList<>();
		Provider provider1 = Provider.builder().id("1").name("P1").prefix("0034").cost(1).build();
		list.add(provider1);
		Provider provider2 = Provider.builder().id("2").name("P2").prefix("0034").cost(2).build();
		list.add(provider2);
		Provider provider3 = Provider.builder().id("3").name("P3").prefix("0034").cost(1).build();
		list.add(provider3);
		return list;
	}

}
