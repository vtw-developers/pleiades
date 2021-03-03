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
package com.vtw.pleiades.center.management.server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vtw.pleiades.center.common.web.validation.ValidationResult;
import com.vtw.pleiades.center.management.system.IntegrationSystem;
import com.vtw.pleiades.center.management.system.IntegrationSystemRepository;

@Service
public class IntegrationServerService {

	@Autowired
	private IntegrationServerRepository repository;
	
	@Autowired
	private IntegrationSystemRepository systemRepository;
	
	@Autowired
	private IntegrationServerValidator validator;

	public Page<IntegrationServerView> list(String keyword, Pageable pageable) {
		return repository.findAllByNameContainsOrSystem_NameContains(keyword, keyword, pageable);
	}
	
	public IntegrationServer get(Long id) {
		return repository.findById(id).get();
	}
	
	public ValidationResult createWithValidation(IntegrationServer server) {
		setSystemByName(server);
		ValidationResult validation = validator.validate(server);
		if (validation.isValid()) {
			create(server);
		}
		return validation;
	}
	
	public ValidationResult updateWithValidation(Long id, IntegrationServer server) {
		setSystemByName(server);
		ValidationResult validation = validator.validate(id, server);
		if (validation.isValid()) {
			update(id, server);
		}
		return validation;
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public IntegrationServer create(IntegrationServer server) {
		return repository.save(server);
	}
	
	public IntegrationServer update(Long id, IntegrationServer newServer) {
		IntegrationServer oldServer = repository.findById(id).orElse(newServer);
		oldServer.setName(newServer.getName());
		oldServer.setDescription(newServer.getDescription());
		
		IntegrationSystem newSystem = systemRepository.findById(newServer.getSystem().getId()).get();
		oldServer.setSystem(newSystem);
		
		return repository.save(oldServer);
	}
	
	private void setSystemByName(IntegrationServer server) {
		IntegrationSystem system = server.getSystem();
		if (system.getId() == null && system.getName() != null) {
			List<IntegrationSystem> systems = systemRepository.findAllByName(system.getName());
			if (systems.size() > 0) {
				server.setSystem(systems.get(0));
			}
		}
	}
}
