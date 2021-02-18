package com.vtw.pleiades.center.system;

import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(IntegrationSystemController.class)
class IntegrationSystemTestControllerTests {

	@Autowired
	MockMvc mvc;

	@MockBean
	private IntegrationSystemService service;
	
	@MockBean
	private IntegrationSystemRepository repository;
	
	@Test
	void getListTest() throws Exception {
		IntegrationSystem system = new IntegrationSystem("테스트 연계시스템", "시스템 설명입니다.");
		system.setId(1L);
		
		PageRequest pageable = PageRequest.of(0, 10, Sort.by(new ArrayList<>()));
		given(service.list(pageable, "테스트 연계", "시스템 설명")).willReturn(Arrays.asList(system));
		System.out.println(service.list(pageable, "테스트 연계", "시스템 설명"));
		final ResultActions actions = mvc
				.perform(get("/systems?page=0&size=10&name=테스트 연계&description=시스템 설명").contentType(MediaType.APPLICATION_JSON))
				.andDo(print());
		actions.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].name", is("테스트 연계시스템"))).andDo(print());
	}
}
