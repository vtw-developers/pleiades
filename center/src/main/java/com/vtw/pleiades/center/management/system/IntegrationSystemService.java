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
package com.vtw.pleiades.center.management.system;

import java.util.List;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vtw.pleiades.center.management.server.IntegrationServer;
import com.vtw.pleiades.center.management.server.IntegrationServerView;

@Service
public class IntegrationSystemService {

	@Autowired
	private IntegrationSystemRepository repository;
	
	public Page<IntegrationSystem> list(Pageable pageable, String name, String description) {
		return repository.findAll(IntegrationSystemSpecs.filter(name, description), pageable);
	}
	
	public IntegrationSystem getSystem(Long id) {
		return repository.findById(id).get();
	}
	
	public IntegrationSystem create(IntegrationSystem system) {
		return repository.save(system);
	}
	
	public IntegrationSystem update(Long id, IntegrationSystem newSystem) {
		IntegrationSystem oldSystem = repository.findById(id).orElse(newSystem);
		oldSystem.setName(newSystem.getName());
		oldSystem.setDescription(newSystem.getDescription());
		return repository.save(oldSystem);
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public boolean exist(String name) {
		List<IntegrationSystem> systems = repository.findAllByName(name);
		return systems.size() > 0;
	}
	
	public List<IntegrationSystem> findAll() {
		return IterableUtils.toList(repository.findAll());
	}

	public List<IntegrationServer> getServers(Long id) {
		return repository.findById(id).get().getServers();
	}
}
