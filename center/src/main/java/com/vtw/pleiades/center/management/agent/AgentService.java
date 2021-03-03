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
package com.vtw.pleiades.center.management.agent;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vtw.pleiades.center.common.web.validation.ValidationResult;
import com.vtw.pleiades.center.management.server.IntegrationServer;
import com.vtw.pleiades.center.management.server.IntegrationServerRepository;

@Service
public class AgentService {

	@Autowired
	private AgentRepository repository;
	
	@Autowired
	private IntegrationServerRepository serverRepository;
	
	@Autowired
	private AgentValidator validator;

	public Page<AgentView> list(String keyword, Pageable pageable) {
		return repository.findAllByNameContainsOrServer_NameContains(keyword, keyword, pageable);
	}
	
	public Agent get(Long id) {
		return repository.findById(id).get();
	}
	
	public ValidationResult createWithValidation(Agent agent) {
		setServerByName(agent);
		ValidationResult validation = validator.validate(agent);
		if (validation.isValid()) {
			create(agent);
		}
		return validation;
	}
	
	public ValidationResult updateWithValidation(Long id, Agent server) {
		setServerByName(server);
		ValidationResult validation = validator.validate(id, server);
		if (validation.isValid()) {
			update(id, server);
		}
		return validation;
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public Agent create(Agent server) {
		return repository.save(server);
	}
	
	public Agent update(Long id, Agent newAgent) {
		Agent oldAgent = repository.findById(id).orElse(newAgent);
		oldAgent.setName(newAgent.getName());
		oldAgent.setDescription(newAgent.getDescription());
		
		IntegrationServer newSystem = serverRepository.findById(newAgent.getServer().getId()).get();
		oldAgent.setServer(newSystem);
		
		return repository.save(oldAgent);
	}
	
	private void setServerByName(Agent agent) {
		IntegrationServer server = agent.getServer();
		if (server.getId() == null && server.getName() != null) {
			List<IntegrationServer> servers = serverRepository.findAllByName(server.getName());
			if (servers.size() > 0) {
				agent.setServer(servers.get(0));
			}
		}
	}
}
