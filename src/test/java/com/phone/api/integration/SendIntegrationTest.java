package com.phone.api.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.phone.api.dao.ProviderDao;
import com.phone.api.dto.MessageDto;
import com.phone.api.dto.ResponseDto;
import com.phone.api.model.Provider;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@FixMethodOrder(NAME_ASCENDING)
public class SendIntegrationTest {

	@Autowired
	private ProviderDao dao;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void test000_cleanDb() throws Exception {
		dao.deleteAll();
	}

	@Test
	public void test001_shouldAddProviders() {
		Provider provider1 = Provider.builder().id("1").name("P1").prefix("0034").cost(1).build();
		dao.save(provider1);
		Provider provider2 = Provider.builder().id("2").name("P2").prefix("0034").cost(2).build();
		dao.save(provider2);
		Provider provider3 = Provider.builder().id("3").name("P3").prefix("0034").cost(1).build();
		dao.save(provider3);
		Provider provider4 = Provider.builder().id("4").name("P3").prefix("0033").cost(3).build();
		dao.save(provider4);
	}

	@Test
	public void test002_sendMessage_1_provider() throws Exception {
		final String path = "/message/0033777111222";

		MessageDto dto = new MessageDto();
		dto.setBody("Hello1");

		ParameterizedTypeReference<List<ResponseDto>> responseType = new ParameterizedTypeReference<List<ResponseDto>>() {
		};

		HttpEntity<MessageDto> request = new HttpEntity<>(dto);
		final ResponseEntity<List<ResponseDto>> response = restTemplate.exchange(path, POST, request, responseType);
		assertEquals(OK, response.getStatusCode());
		assertEquals(1, response.getBody().size());
		assertEquals("P3", response.getBody().get(0).getProviderName());
		assertNotNull(response.getBody().get(0).getOperationId());

	}

	@Test
	public void test003_sendMessage_0_providers() throws Exception {
		final String path = "/message/0035777111222";

		MessageDto dto = new MessageDto();
		dto.setBody("Hello2");

		ParameterizedTypeReference<List<ResponseDto>> responseType = new ParameterizedTypeReference<List<ResponseDto>>() {
		};

		HttpEntity<MessageDto> request = new HttpEntity<>(dto);
		final ResponseEntity<List<ResponseDto>> response = restTemplate.exchange(path, POST, request, responseType);
		assertEquals(OK, response.getStatusCode());
		assertEquals(0, response.getBody().size());

	}

	@Test
	public void test004_sendMessage_2_providers() throws Exception {
		final String path = "/message/0034666111222";

		MessageDto dto = new MessageDto();
		dto.setBody("Hello3");

		ParameterizedTypeReference<List<ResponseDto>> responseType = new ParameterizedTypeReference<List<ResponseDto>>() {
		};

		HttpEntity<MessageDto> request = new HttpEntity<>(dto);
		final ResponseEntity<List<ResponseDto>> response = restTemplate.exchange(path, POST, request, responseType);
		assertEquals(OK, response.getStatusCode());
		assertEquals(2, response.getBody().size());
		assertEquals("P1", response.getBody().get(0).getProviderName());
		assertNotNull(response.getBody().get(0).getOperationId());
		assertEquals("P3", response.getBody().get(1).getProviderName());
		assertNotNull(response.getBody().get(1).getOperationId());
	}

}
