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

@Service
public class IntegrationServerService {

	@Autowired
	private IntegrationServerRepository repository;

	public Page<IntegrationServerView> list(String name, String description, Pageable pageable) {
		return repository.findAllByNameContainsAndDescriptionContains(name, description, pageable);
	}
	
	public IntegrationServer get(Long id) {
		return repository.findById(id).get();
	}
	
	public IntegrationServer create(IntegrationServer server) {
		return repository.save(server);
	}
	
	public IntegrationServer update(Long id, IntegrationServer newServer) {
		IntegrationServer oldSystem = repository.findById(id).orElse(newServer);
		oldSystem.setName(newServer.getName());
		oldSystem.setDescription(newServer.getDescription());
		return repository.save(oldSystem);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public boolean exist(String name) {
		List<IntegrationServer> systems = repository.findByName(name);
		return systems.size() > 0;
	}
}
