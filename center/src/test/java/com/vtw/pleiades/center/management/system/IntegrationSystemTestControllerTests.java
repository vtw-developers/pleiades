package com.vtw.pleiades.center.management.system;

import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
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

import com.vtw.pleiades.center.management.server.IntegrationServer;

@WebMvcTest(IntegrationSystemController.class)
class IntegrationSystemTestControllerTests {

	@Autowired
	MockMvc mvc;

	@MockBean
	private IntegrationSystemService service;
	
	@MockBean
	private IntegrationSystemRepository repository;
	
	@Test
	void testList() throws Exception {
		IntegrationSystem system = new IntegrationSystem("테스트 연계시스템", "시스템 설명입니다.");
		
		PageRequest pageable = PageRequest.of(0, 10, Sort.by(new ArrayList<>()));
		given(service.list(pageable, "테스트 연계")).willReturn(new PageImpl<>(Arrays.asList(new IntegrationSystemView() {
			@Override
			public String getName() {
				return system.getName();
			}
			
			@Override
			public Long getId() {
				return system.getId();
			}
			
			@Override
			public String getDescription() {
				return system.getDescription();
			}

			@Override
			public List<IntegrationServer> getServers() {
				return system.getServers();
			}
		}), pageable, 1));
		final ResultActions actions = mvc
				.perform(get("/systems?page=0&size=10&name=테스트 연계").contentType(MediaType.APPLICATION_JSON))
				.andDo(print());
		actions.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.content[0].name", is("테스트 연계시스템"))).andDo(print());
	}
}
