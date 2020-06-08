package com.phone.api.unitTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.phone.api.dto.ResponseDto;
import com.phone.api.service.SendService;

@RunWith(SpringRunner.class)
@WebMvcTest
public class MessageControllerTest {

	@Autowired
	protected MockMvc mockMvc;
	@MockBean
	protected SendService sendService;

	@Test
	public void test001_shouldReturnResponse1Provider() throws Exception {
		String content = "{\"body\": \"Hello\"}";

		when(sendService.send(any(), any())).thenReturn(generateResponse());

		mockMvc.perform(
				post("/message/0034666111222").content(content).contentType(APPLICATION_JSON).accept(APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", hasSize(1)))
			.andExpect(content().contentType(APPLICATION_JSON))
			.andExpect(jsonPath("$[0].operationId", is("1")))
			.andExpect(jsonPath("$[0].providerName", is("P1")));
	}

	private List<ResponseDto> generateResponse() {
		List<ResponseDto> list = new ArrayList<>();
		ResponseDto dto = ResponseDto.builder().operationId("1").providerName("P1").build();
		list.add(dto);
		return list;
	}

}
