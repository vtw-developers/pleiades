package com.vtw.pleiades.center.management.server;

import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

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

import com.vtw.pleiades.center.management.agent.Agent;
import com.vtw.pleiades.center.management.system.IntegrationSystem;

@WebMvcTest(IntegrationServerController.class)
class IntegrationServerTestControllerTests {

	@Autowired
	MockMvc mvc;

	@MockBean
	private IntegrationServerService service;
	
	@MockBean
	private IntegrationServerRepository repository;
	
	@Test
	void testList() throws Exception {
		IntegrationSystem system = new IntegrationSystem("테스트 연계시스템", "시스템 설명입니다.");
		IntegrationServer server = new IntegrationServer(system, "테스트 연계서버", "서버 설명입니다.");
		
		PageRequest pageable = PageRequest.of(0, 10, Sort.unsorted());
		given(service.list("서버", null, null, pageable)).willReturn(new PageImpl<>(Arrays.asList(new IntegrationServerView() {
			
			@Override
			public IntegrationSystem getSystem() {
				return server.getSystem();
			}
			
			@Override
			public String getName() {
				return server.getName();
			}
			
			@Override
			public Long getId() {
				return server.getId();
			}
			
			@Override
			public String getDescription() {
				return server.getDescription();
			}

			@Override
			public List<Agent> getAgents() {
				return server.getAgents();
			}
		}), pageable, 1));
		final ResultActions actions = mvc
				.perform(get("/servers?page=0&size=10&name=서버&description=서버 설명&systemName=시스템").contentType(MediaType.APPLICATION_JSON))
				.andDo(print());
		actions.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.content[0].name", is("테스트 연계서버"))).andDo(print());
	}
}
