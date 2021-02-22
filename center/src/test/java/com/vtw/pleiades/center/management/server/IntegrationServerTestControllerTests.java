package com.vtw.pleiades.center.management.server;

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
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(IntegrationServerController.class)
class IntegrationServerTestControllerTests {

	@Autowired
	MockMvc mvc;

	@MockBean
	private IntegrationServerService service;
	
	@MockBean
	private IntegrationServerRepository repository;
	
//	@Test
//	void getListTest() throws Exception {
//		IntegrationServer server = new IntegrationServer("테스트 연계시스템", "시스템 설명입니다.");
//		
//		PageRequest pageable = PageRequest.of(0, 10, Sort.by(new ArrayList<>()));
//		given(service.list(pageable, "테스트 연계", "시스템 설명")).willReturn(new PageImpl<>(Arrays.asList(server), pageable, 1));
//		System.out.println(service.list(pageable, "테스트 연계", "시스템 설명"));
//		final ResultActions actions = mvc
//				.perform(get("/servers?page=0&size=10&name=테스트 연계&description=시스템 설명").contentType(MediaType.APPLICATION_JSON))
//				.andDo(print());
//		actions.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
//				.andExpect(jsonPath("$[0].name", is("테스트 연계시스템"))).andDo(print());
//	}
}