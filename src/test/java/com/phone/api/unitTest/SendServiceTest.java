package com.phone.api.unitTest;

import static org.junit.Assert.assertNotNull;
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

import com.phone.api.dto.ResponseDto;
import com.phone.api.model.Provider;
import com.phone.api.service.ProviderService;
import com.phone.api.service.impl.SendServiceImpl;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SendServiceTest {
	@Mock
	private ProviderService providerService;
	
	@InjectMocks
	private SendServiceImpl sendService;
	
	@Test
	public void test001_shouldSendMessage() {
		String mobile = "0034666111222";
		String message = "Hello Wolrd!";
		List<Provider> providerList = generateProviderList();
		
		when(providerService.getProvidersByPrefix("0034")).thenReturn(providerList);

		List<ResponseDto> dto = sendService.send(mobile, message);
		
		assertEquals(2, dto.size());
		assertEquals(dto.get(0).getProviderName(), "P1");
		assertNotNull(dto.get(0).getOperationId());
		assertEquals(dto.get(1).getProviderName(), "P3");
		assertNotNull(dto.get(1).getOperationId());
	}
	
	
	private List<Provider> generateProviderList() {
		List<Provider> list = new ArrayList<>();
		Provider provider1 = Provider.builder().id("1").name("P1").prefix("0034").cost(1).build();
		list.add(provider1);
		Provider provider2 = Provider.builder().id("3").name("P3").prefix("0034").cost(1).build();
		list.add(provider2);
		return list;
	}
}
