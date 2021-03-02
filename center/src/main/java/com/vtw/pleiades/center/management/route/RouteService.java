/*
 * Copyright 2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.vtw.pleiades.center.management.route;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vtw.pleiades.center.common.web.validation.ValidationResult;
import com.vtw.pleiades.center.management.agent.Agent;
import com.vtw.pleiades.center.management.agent.AgentRepository;

@Service
public class RouteService {

	@Autowired
	private RouteRepository repository;
	
	@Autowired
	private AgentRepository agentRepository;

	public Page<RouteView> list(String keyword, Pageable pageable) {
		return repository.findAllByNameContainsOrAgent_NameContains(keyword, keyword, pageable);
	}
	
	public Route get(Long id) {
		return repository.findById(id).get();
	}
	
	public Route create(Route server) {
		return repository.save(server);
	}
	
	public Route update(Long id, Route newRoute) {
		Route oldRoute = repository.findById(id).orElse(newRoute);
		oldRoute.setName(newRoute.getName());
		oldRoute.setDescription(newRoute.getDescription());
		
		Agent newAgent = agentRepository.findById(newRoute.getAgent().getId()).get();
		oldRoute.setAgent(newAgent);
		
		return repository.save(oldRoute);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public ValidationResult validate(Route server) {
		ValidationResult hasParent = validateHasParent(server);
		if (hasParent.isInvalid()) {
			return hasParent;
		}
		ValidationResult notExistName = validateNotExistName(server);
		return notExistName;
	}
	
	public ValidationResult validate(Long id, Route server) {
		ValidationResult hasParent = validateHasParent(server);
		if (hasParent.isInvalid()) {
			return hasParent;
		}
		ValidationResult notExistName = validateNotExistName(id, server);
		return notExistName;
	}
	
	public ValidationResult validateHasParent(Route route) {
		if (route.getAgent().getId() == null) {
			return ValidationResult.invalid("noParent,system");
		}
		
		boolean exist = agentRepository.existsById(route.getAgent().getId());
		if (!exist) {
			return ValidationResult.invalid("noParent,system");
		}
		return ValidationResult.valid();
	}
	
	public ValidationResult validateNotExistName(Route server) {
		boolean exist = exist(server.getName());
		if (exist) {
			return ValidationResult.invalid("exist,name");
		}
		return ValidationResult.valid();
	}
	
	public ValidationResult validateNotExistName(Long id, Route server) {
		boolean exist = exist(id, server.getName());
		if (exist) {
			return ValidationResult.invalid("exist,name");
		}
		return ValidationResult.valid();
	}
	
	public boolean exist(String name) {
		List<Route> systems = repository.findAllByName(name);
		return systems.size() > 0;
	}
	
	/**
	 * 변경 시 서버명 중복검사
	 * 
	 * @param id 변경대상 서버ID
	 * @param name 변경되는 서버명
	 * @return 이미 존재하는 서버명일 경우 true
	 */
	public boolean exist(Long id, String name) {
		// 서버명을 변경하지 않았을 경우 false를 리턴
		Route oldServer = repository.findById(id).get();
		if (StringUtils.equals(oldServer.getName(), name)) {
			return false;
		}
		
		List<Route> systems = repository.findAllByName(name);
		return systems.size() > 0;
	}
}
